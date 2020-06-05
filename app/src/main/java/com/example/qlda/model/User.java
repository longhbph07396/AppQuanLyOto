package com.example.qlda.model;

import androidx.annotation.Nullable;

public class User {
    private String userName, passWord;
    private int id;

    public User(String userName, String passWord ) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userName, String passWord, int id) {
        this.userName = userName;
        this.passWord = passWord;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof User) {
            return userName.equals(((User) obj).getUserName());
        }
        return super.equals(obj);
    }
}
