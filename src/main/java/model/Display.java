package model;

import java.util.List;
import java.util.Optional;

/**
 * Created by Paul on 15.10.2015.
 */
public class Display {
    public void createHeader(){
        System.out.println("\nEPOST\t\t\t\tPASSORD\t\tJOBBTYPE\n*******************************************");
    }

    public void createUser(User user){
        System.out.format("Opprettet bruker: %d\t%s\t\t%s\t\t%s\n",
                user.getId(), user.getEmail(), user.getPassword(), user.getWorkType() );
    }
    public void errorCreatUser(User user){
        System.err.println("En feil oppdages imens du prøvde å og opprette " + user.toString());
    }

    public void updateUser(User user){
        System.out.format("Oppdatert bruker: %d\t%s\t\t%s\t\t%s\n", user.getId(), user.getEmail(), user.getPassword(), user.getWorkType());
    }
    public void errorUpdated(User user){
        System.err.println("En feil oppdages imens du prøvde å og opprette " + user.toString());
    }

    public void deleteUser(int id){
        System.err.println("Bruker slettet med id: " + id);
    }
    public void errorDeletUser(int id){
        System.err.println("Ingen bruker funnet med id.. " + id);
    }

    public void getAllUsers(List<User> allUsers){

        allUsers.forEach(System.out::println);
    }
    public void errorGetAllUsers(List<User> user){
        System.err.println("En feil oppdages imens du prøvde å hente alle brukere " + user.toString());
    }

    public void updataAUserH2(User user){
        System.out.format("Bruker er oppdatert.\t\t %s\t\t%s\t\t%s\n", user.getEmail(), user.getPassword(), user.getWorkType());
    }

    public void getUserById(Optional<User> userById) {
        System.out.println("Funnet bruker "+ "ID:"+userById.get().getId() + "\tEpost: " + userById.get().getEmail() + "\tPassord:" + userById.get().getPassword() + "\tJobbtype" + userById.get().getWorkType() );
    }

    public void dropTable(String tableName){
        System.out.println("Table: " + tableName + " where dropped!");
    }
}