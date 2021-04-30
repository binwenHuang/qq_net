package edu.cdp.qq.qq.bean;

import java.io.Serializable;

public class User implements Serializable{
    private String id;
    private String password;

    public User(String id, String password) {
        super();
        // TODO Auto-generated constructor stub
        this.id = id;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ",pwd="+ password + "]";
    }


}
