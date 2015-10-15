package infrastructureTest;

import infrastructure.ArrayUserDAO;
import model.Display;
import model.Type;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * ArrayUserDAO Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>okt 9, 2015</pre>
 */
public class ArrayUserDAOTest {

    private List<User> listOfUsers;
    private ArrayUserDAO arrayUserDAO;


    @Before
    public void setUp() {
        arrayUserDAO = new ArrayUserDAO();

    }

    @Test
    public void tearDown() {

    }

    /**
     * Method: getListOfAllUsers()
     */
    @Test
    public void testGetListOfAllUsers() throws Exception {

        System.out.println("\n1) Test skriv ut alle brukere: ");
        arrayUserDAO.createUser(new User(2, "per@yahoo.no", "passord123", Type.TEACHER));
        arrayUserDAO.createUser(new User(3, "kai@yahoo.no", "passord344", Type.STUDENT));
        arrayUserDAO.createUser(new User(4, "dia@yahoo.no", "passord555", Type.TEACHER));
        arrayUserDAO.createUser(new User(5, "pia@yahoo.no", "passord636", Type.STUDENT));

        int isNotNull = arrayUserDAO.getListOfAllUsers().size();

        Assert.assertNotNull(isNotNull);
    }

/**
 *
 * Method: setListOfAllUsers(List<User> listOfAllUsers)
 *
 */

    /**
     * Method: createUser(User user)
     */
    @Test
    public void testCreateUser() throws Exception {
        System.out.println("3) Test opprettet bruker: ");
        boolean isCreated = arrayUserDAO.createUser(new User(2, "Fra@yahoo.no", "passord123", Type.TEACHER));

        Assert.assertTrue(isCreated);

    }

    /**
     * ﬂ
     * <p>
     * Method: updateUser(User user)
     */
    @Test
    public void testUpdateUser() throws Exception {

        System.out.println("4) Teste oppdater/endre bruker: ");
        User user = new User(2, "per@yahoo.no", "passord123", Type.TEACHER);
        arrayUserDAO.createUser(user);
        User user2 = new User(2, "ole@yahoo.no", "parrso", Type.STUDENT);
        arrayUserDAO.updateUser(user2);
        Assert.assertNotSame(user,user2 );

    }

    /**
     * Method: getUserById(int id)
     */
    @Test
    public void testGetUserById() throws Exception {
        arrayUserDAO.createUser(new User(7, "leo@yahoo.no", "passord1234", Type.TEACHER));

        Display display = new Display();
        Optional<User> user;
        int id = 7;
        user =  arrayUserDAO.getUserById(id);
        display.getUserById(user);

        Assert.assertTrue(user.isPresent());
    }

    /**
     * Method: getAllUsers()
     */
    @Test
    public void testGetAllUsers() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: deleteUser(int id)
     */
    @Test
    public void testDeleteUser() throws Exception {
//TODO: Test goes here...
    }

    @Test
    public void testSetListOfAllUsers() throws Exception {
        // TODO
    }

}