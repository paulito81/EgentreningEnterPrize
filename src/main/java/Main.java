import domain.UserService;
import infrastructure.H2DAO;
import infrastructure.JPADAO;
import infrastructure.UserDAO;
import model.Type;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Paul on 15.10.2015.
 */
public class Main {
    @JPADAO
    private JPADAO jpadao;
    @H2DAO
    private H2DAO h2DAO;
    // @Inject
    UserDAO userDao;

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    public static void main(String[] args) {

        WeldContainer container = new Weld().initialize();
        UserService userService = container.instance().select(UserService.class).get();

        //    createArrayList(userService);
        createH2(userService);

    }

    public static void createH2(UserService userService) {
        System.out.println("-----H2 PRINTOUT:------");
        userService.createUserH2(20, "ola@yahoo.no", "passord8", Type.STUDENT);
        userService.createUserH2(30, "Gun@yahoo.no", "passord2", Type.TEACHER);
        userService.createUserH2(40, "kei@yahoo.no", "passord3", Type.STUDENT);
        userService.createUserH2(50, "lars@yahoo.no", "passord4", Type.TEACHER);
        userService.createUserH2(60, "silje@yahoo.no", "passord5", Type.STUDENT);
        // userService.dropTable("USER");

        userService.getAllUsersH2();
        System.out.println("");
        //  userService.updateUserH2(new User(20, "Gun@yahoo.no", "passordrr", Type.STUDENT));
        //    userService.getUserByIDH2(20);

    }

    public static void createArrayList(UserService userService) {
        System.out.println("<<<<<<ARRAYLIST PRINTOUT:>>>>>>");
        userService.createUser(1, "ola@yahoo.no", "passord8", Type.STUDENT);
        userService.createUser(2, "Gun@yahoo.no", "passord2", Type.TEACHER);
        userService.createUser(3, "kei@yahoo.no", "passord3", Type.STUDENT);
        userService.createUser(4, "lars@yahoo.no", "passord4", Type.TEACHER);
        userService.createUser(5, "silje@yahoo.no", "passord5", Type.STUDENT);
        //  userService.updateUser(new User(2, "erik@yahoo.no", "passord4", Type.STUDENT));
        // userService.getAllUsers();
    }
}