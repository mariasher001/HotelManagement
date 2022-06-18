package com.marry.hotelmanagement.Models;

import javax.persistence.*;

@Entity(name = "UserData")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic(optional = false)
    private String username;
    @Basic(optional = false)
    private String email;
    @Basic(optional = false)
    private String password;

    //Empty constructor
    public UserData() {

    }

    //Getter&Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
