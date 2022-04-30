package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private Integer userId;

    private String login;

    private String password;

    private ArrayList<ChatRoom> roomsOwner;

    private ArrayList<ChatRoom> roomsSocial;

    public User(Integer userId, String login, String password, ArrayList<ChatRoom> roomsOwner, ArrayList<ChatRoom> roomsSocial) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.roomsOwner = roomsOwner;
        this.roomsSocial = roomsSocial;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, roomsOwner, roomsSocial);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        User usr = (User) obj;
        return  usr.userId.equals(userId) &&
                usr.login.equals(login) &&
                usr.password.equals(password) &&
                usr.roomsOwner.equals(roomsOwner) &&
                usr.roomsSocial.equals(roomsSocial);
    }

    @Override
    public String toString() {
        return "User - " +
                "id = " + userId +
                ", login " + login +
                ", with password " + password +
                ", created rooms " + roomsOwner +
                ", participating in " + roomsSocial + " rooms";
    }
}
