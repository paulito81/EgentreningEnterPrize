package model;

import com.sun.istack.internal.NotNull;
import infrastructure.H2DAO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by Paul on 15.10.2015.
 */
@H2DAO
public class User {

    private int id;
    @NotNull
    //Pattern regexp
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type workType;

    public User(int id, String email, String password, Type workType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.workType = workType;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getWorkType() {
        return workType;
    }

    public void setWorkType(Type workType) {
        this.workType = workType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", workType=" + workType +
                '}';
    }
}