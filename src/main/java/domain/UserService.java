package domain;

import infrastructure.ArrayUserDAO;
import infrastructure.H2UserDAO;
import infrastructure.UserDAO;
import model.Display;
import model.Type;
import model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 15.10.2015.
 */
public class UserService {


    //@H2DAO
    H2UserDAO h2UserDAO;
    ArrayUserDAO arrayUserDAO;
    // @Inject
    UserDAO userDAO;
    //PRINT OUT
    Display display = new Display();


    UserService() {
        //  h2UserDAO = new H2UserDAO();
        arrayUserDAO = new ArrayUserDAO();
        display.createHeader();
    }

    // ARRAYLIST
    public void createUser(int id, String email, String password, Type workType) {
        if (id != 0 || email != null || password != null || workType != null) {

            User user = new User(id, email, password, workType);
            display.createUser(user);
            arrayUserDAO.createUser(user);
        }else
            System.out.println("Your value is invalid it contains 0!");
    }

    public void updateUser(User user) {
        if(user !=null){
            arrayUserDAO.updateUser(user);
            System.out.println("\n");
            display.updateUser(user);
        }else
            System.out.println("No value found!");
    }

    public Optional<User> getUserByID(int id) {
        if (id != 0) {

            arrayUserDAO.getUserById(id);
            display.getUserById(userDAO.getUserById(id));
            return userDAO.getUserById(id);
        } else
            return null;
    }

    public List<User> getAllUsers() {
        if (getAllUsers() != null) {
            display.getAllUsers(arrayUserDAO.getAllUsers());
            return userDAO.getAllUsers();
        } else
            return null;
    }

    public boolean deleteAUser(int id) {
        return userDAO.deleteUser(id);
    }


    // H2DATABASE
    public boolean createUserH2(int id, String email, String password, Type workType) {

        if (id != 0 || email != null || password != null || workType != null) {
            User user = new User(id, email, password, workType);
            H2UserDAO h2UserDAO = new H2UserDAO();
            display.createUser(user);
            h2UserDAO.createUser(user);

            return true;

        } else
            return false;

    }

    public boolean updateUserH2(User user) {
        if (user != null) {
            H2UserDAO h2UserDAO = new H2UserDAO();
            h2UserDAO.updateUser(user);
            display.updataAUserH2(user);
            return true;
        } else
            return false;

    }

    public Optional<User> getUserByIDH2(int id) {
        if (id != 0) {
            H2UserDAO h2UserDAO = new H2UserDAO();
            display.getUserById(h2UserDAO.getUserById(id));
            return h2UserDAO.getUserById(id);
        } else
            return null;
    }

    public List<User> getAllUsersH2() {

        H2UserDAO h2UserDAO = new H2UserDAO();
        display.getAllUsers(h2UserDAO.getAllUsers());
        return h2UserDAO.getAllUsers();

    }

    public boolean deleteAUserH2(int id) {
        if( id !=0){

            return userDAO.deleteUser(id);
        }
        else
            return false;
    }

    public void dropTable(String tableName){

        H2UserDAO h2UserDAO = new H2UserDAO();
        h2UserDAO.dropTable(tableName);
        display.dropTable(tableName);

    }
}
