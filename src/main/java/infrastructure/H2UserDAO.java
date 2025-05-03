package infrastructure;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Type;
import model.User;
import org.h2.tools.RunScript;

@H2DAO
public class H2UserDAO implements UserDAO {

    private Connection connection;

    public H2UserDAO() {
        connectToH2();
    }

    private synchronized void connectToH2() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:userDB", "sa", "");
            RunScript.execute(connection, new FileReader("db/init.sql"));
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void closeConnectionToH2() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized boolean createUser(User user) {
        String getNextIdSql = "SELECT NEXTVAL('SEQ_USER')";
        String insertSql = "INSERT INTO \"USER\" (id, email, password, type) VALUES (?, ?, ?, ?)";

        try (
                Statement idStatement = connection.createStatement();
                ResultSet rs = idStatement.executeQuery(getNextIdSql)
        ) {
            if (rs.next()) {
                int nextId = rs.getInt(1);

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                    preparedStatement.setInt(1, nextId);
                    preparedStatement.setString(2, user.getEmail());
                    preparedStatement.setString(3, user.getPassword());
                    preparedStatement.setString(4, user.getWorkType().name());

                    preparedStatement.executeUpdate();
                    user.setId(nextId);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public synchronized boolean updateUser(User user) {
        String sqlUpdate = "UPDATE \"USER\" SET email = ?, password = ?, type = ? WHERE id = ?";

        if (user != null && user.getEmail() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getWorkType().name());
                preparedStatement.setInt(4, user.getId());

                int rows = preparedStatement.executeUpdate();

                if (rows != 1) {
                    throw new IllegalStateException("Wrong number of updated rows! " + rows + "\nID: " + user.getId());
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public synchronized Optional<User> getUserById(int id) {
        String getUserById = "SELECT * FROM \"USER\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserById)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setWorkType(Type.valueOf(resultSet.getString("type")));
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public synchronized List<User> getAllUsers() {
        String listOfUserSQL = "SELECT * FROM \"USER\"";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(listOfUserSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setWorkType(Type.valueOf(resultSet.getString("type")));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public synchronized boolean deleteUser(int id) {
        String deleteUserSQL = "DELETE FROM \"USER\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();

            if (rows != 1) {
                throw new IllegalStateException("Wrong number of deleted rows! " + rows + "\nID: " + id);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean dropTable(String tableName) {
        String dropTable = "DROP TABLE IF EXISTS \"" + tableName + "\"";

        try (PreparedStatement preparedStatement = connection.prepareStatement(dropTable)) {
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}