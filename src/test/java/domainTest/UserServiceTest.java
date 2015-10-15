package domainTest;

import model.Type;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Paul on 15.10.2015.
 */
public class UserServiceTest {
    // user tests
    User user1;
    User user2;
    User user3;
    User user4;

    @Before
    public void setUp(){
        // id, mail, password, worktype
        user1 = new User(300,"peterpan@yahoo.no","123456789", Type.STUDENT );
        user2 = new User(301,"wendy@yahoo.no","abcdefgh", Type.STUDENT );
        user3 = new User(302,"james@yahoo.no","ijklmnopqr", Type.TEACHER );
        user4 = new User(303,"olav@yahoo.no","awsdjiklm", Type.TEACHER );

    }

    @After
    public void tearDown(){
        // delete all users.
        user1 = null;
        user2 = null;
        user3 = null;
        user4 = null;

    }


    @Test
    public void createUser() throws Exception {
        //TODO return a user fields updated
    }
    @Test
    public void updateUser(){
        //TODO return user fields updated
    }
    @Test
    public void getUserByID(){
        //TODO get a user with id  -return user true

    }
    @Test
    public void getAllUsers(){
        //TODO get all users return a value not null
    }

    @Test
    public void deleteAUser(){
        //TODO return true if user id == 0 || notExist
    }


}