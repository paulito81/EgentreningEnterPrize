package infrastructureTest;

import infrastructure.H2UserDAO;
import model.Type;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 15.10.2015.
 */
public class H2UserDAOIT {
    private H2UserDAO userDAO ;


    @Before
    public void setUp(){

        userDAO = new H2UserDAO();

    }
    @After
    public void tearDown(){
        userDAO.closeConnectionToH2();
    }

    @Test
    public void createANewUser(){
        boolean created = userDAO.createUser(new User(2,"ole@yahoo.no", "passord", Type.STUDENT));
        Optional<User> user = userDAO.getUserById(50);
        System.out.println("1) Test opprettelse av ´ny bruker: \tID:" + user.get().getId() + "\tEpost:" + user.get().getEmail() +"\tPassord:"+ user.get().getPassword() + "\t\tJobb:" + user.get().getWorkType());
        Assert.assertTrue(created);
    }

    @Test
    public void updateAUser(){
        Optional<User> user = userDAO.getUserById(1);
        if(user.isPresent()) {
            System.out.println("2) Test oppdatering av ´bruker X: \tID:" + user.get().getId() + "\tEpost:" + user.get().getEmail() + "\tPassord:"+ user.get().getPassword()+ "\tJobb:" + user.get().getWorkType());
        }

        boolean isUpdated = userDAO.updateUser(new User(1, "fredrik@yahoo.no", "gutt1234", Type.STUDENT));
        Assert.assertTrue(isUpdated);
    }

    @Test
    public void getAUserById(){
        Optional<User> user = userDAO.getUserById(1);
        if(user.isPresent() ){
            System.out.println("3) Test hente bruker med ´ID X: \tID:" + user.get().getId()  + "\tEpost:" + user.get().getEmail()+ "\tPassord:"+ user.get().getPassword()+ "\tJobb:" + user.get().getWorkType());

        }
        user = userDAO.getUserById(1);
        Assert.assertNotNull(user);

    }
    @Test
    public void getAllUsers(){
        List<User> listOfUsers = new ArrayList<>();
        userDAO.createUser(new User(2,"ole@yahoo.no", "passord0", Type.STUDENT));
        userDAO.createUser(new User(3,"per@yahoo.no", "passord2", Type.TEACHER));
        userDAO.createUser(new User(4,"knus@yahoo.no", "passord3", Type.TEACHER));
        userDAO.createUser(new User(5,"rut@yahoo.no", "passord4", Type.STUDENT));

        for (User user : userDAO.getAllUsers()) {
            listOfUsers.add(user);
            System.out.println("4) Teste hente alle brukere´ \t\tID:" + user.getId()  + "\tEpost:" + user.getEmail()+ "\tPassord:"+ user.getPassword()+ "\tJobb:" + user.getWorkType());
        }
        Assert.assertTrue(!userDAO.getAllUsers().isEmpty());


    }

    @Test
    public void deleteAUser() {
        Optional<User> user = userDAO.getUserById(1);
        user = userDAO.getUserById(1);
        System.out.println("5) Sletter bruker med id: \t\t\tID:" + user.get().getId()  + "\tEpost:" + user.get().getEmail()+ "\tPassord:"+ user.get().getPassword()+ "\tJobb:" + user.get().getWorkType());
        boolean isDeleted = userDAO.deleteUser(1);
        Assert.assertTrue(isDeleted);
    }

}