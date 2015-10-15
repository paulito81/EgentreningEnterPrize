package infrastructure;

import model.Display;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Paul on 15.10.2015.
 */
public class ArrayUserDAO implements UserDAO{


    List<User> listOfAllUsers = new ArrayList<>();
    Display display;

    public List<User> getListOfAllUsers() {
        return listOfAllUsers;
    }


    public ArrayUserDAO() {
        display = new Display();

    }


    @Override
    public boolean createUser(User user) {
        if (user != null ) {
            listOfAllUsers.add(user);
            display.createUser(user);
            return true;
        }
        //   display.errorCreatUser(null);
        return false;

    }

    @Override
    public boolean updateUser(User user) {
        if (listOfAllUsers.size() > 0) {
            List<User> user2 = listOfAllUsers.stream().filter((l)
                    -> l == user).collect(Collectors.toList());

            display.updateUser(user);
            return true;
        }
        //display.errorUpdated(user);
        return false;
    }

    @Override
    public Optional<User> getUserById(int id) {
        User user = new User();
        if(id > 1 && getListOfAllUsers().size() >= 1){
            getListOfAllUsers().contains(user);
            // List<User> user2 = listOfAllUsers.stream().filter((l) -> l.getId() == id).collect(Collectors.toList());

            return Optional.of(new User(user.getId(), user.getEmail(), user.getPassword(), user.getWorkType() ));
        }
        display.getUserById(Optional.empty());
        return Optional.empty();
    }


    @Override
    public List<User> getAllUsers() {
        if(listOfAllUsers.size() > 1) {
            listOfAllUsers.forEach(user -> System.out.println(user.getId() + " " + user.getEmail() + " " + user.getPassword() + " " + user.getWorkType()));
            display.getAllUsers(listOfAllUsers);
            return listOfAllUsers;
        }
        //display.errorGetAllUsers(listOfAllUsers);
        return null;
    }


    @Override
    public boolean deleteUser(int id) {
        if( id >= 1) {
            listOfAllUsers.remove(id);
            display.deleteUser(id);
            return true;
        }else{
            //display.errorDeletUser(id);
            return false;
        }
    }
       /*
    public void setListOfAllUsers(List<User> listOfAllUsers) {

        this.listOfAllUsers = listOfAllUsers;
    }
*/
/*
    public ArrayUserDAO(User user){

        listOfAllUsers.removeAll((Collection<?>) user);
    }
*/
}
