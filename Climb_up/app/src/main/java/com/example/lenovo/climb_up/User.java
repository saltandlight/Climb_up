package com.example.lenovo.climb_up;

/**
 * Created by lenovo on 2018-01-20.
 */

public class User {

    String ID;
    String Password;
    String Name;

    public User(String ID, String Password, String Name) {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;

    }

    public void setUserID(String ID)
    {
        this.ID = ID;
    }

    public void setUserPassword(String Password)
    {
        this.Password = Password;
    }

    public void setUserName(String Name)
    {
        this.Name = Name;
    }

    public String getUserID() {
        return ID;
    }

    public String getUserPassword() {
        return Password;
    }

    public String getUserName() {
        return Name;
    }
}