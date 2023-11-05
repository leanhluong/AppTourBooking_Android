package com.example.apptourbooking.Domain;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int userId;
    private String userName;
    private String fullName;
    private String password;

    private String token;
    private int role;

    public UserInfo() {
    }

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserInfo(int userId, String userName, String fullName, String password, int role) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    public UserInfo(int userId, String userName, String fullName, String password, String token, int role) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    public UserInfo(String userName, String fullName, String password, int role) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
