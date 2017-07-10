package vn.aqtsoft.mapcross.model;

/**
 * Created by Nusib on 7/10/2017.
 */

public class User {
    private String UserName;
    private String Pass;
    private String DateCreate;

    public User(String userName, String pass, String dateCreate) {
        UserName = userName;
        Pass = pass;
        DateCreate = dateCreate;
    }

    public User() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }
}
