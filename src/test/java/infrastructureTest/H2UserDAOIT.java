package infrastructureTest;

import infrastructure.H2UserDAO;
import model.Type;
import model.User;
import org.junit.*;

import java.util.List;
import java.util.Optional;

public class H2UserDAOIT {

    private H2UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO = new H2UserDAO();
    }

    @After
    public void tearDown() {
        userDAO.closeConnectionToH2();
    }

    @Test
    public void createANewUser() {
        User user = new User("ole@yahoo.no", "Passord123!", Type.STUDENT);
        boolean created = userDAO.createUser(user);
        Assert.assertTrue(created);

        List<User> users = userDAO.getAllUsers();
        Optional<User> savedUser = users.stream().filter(u -> u.getEmail().equals("ole@yahoo.no")).findFirst();
        Assert.assertTrue(savedUser.isPresent());
        savedUser.ifPresent(System.out::println);
    }

    @Test
    public void updateAUser() {
        Optional<User> userOpt = userDAO.getUserById(1);
        userOpt.ifPresent(System.out::println);

        boolean updated = userDAO.updateUser(new User( 1, "fredrik@yahoo.no", "Gutt1234!", Type.STUDENT));
        Assert.assertTrue(updated);
    }

    @Test
    public void getAUserById() {
        Optional<User> user = userDAO.getUserById(1);
        Assert.assertTrue(user.isPresent());
        user.ifPresent(System.out::println);
    }

    @Test
    public void getAllUsers() {
        userDAO.createUser(new User("ole@yahoo.no", "passord0!", Type.STUDENT));
        userDAO.createUser(new User("per@yahoo.no", "passord2!", Type.TEACHER));
        userDAO.createUser(new User("knus@yahoo.no", "passord3!", Type.TEACHER));
        userDAO.createUser(new User("rut@yahoo.no", "passord4!", Type.STUDENT));

        List<User> users = userDAO.getAllUsers();
        users.forEach(System.out::println);

        Assert.assertFalse(users.isEmpty());
        Assert.assertEquals(5, users.size());
    }

    @Test
    public void deleteAUser() {
        Optional<User> userOpt = userDAO.getUserById(1);
        userOpt.ifPresent(System.out::println);

        boolean deleted = userDAO.deleteUser(1);
        Assert.assertTrue(deleted);
    }
}