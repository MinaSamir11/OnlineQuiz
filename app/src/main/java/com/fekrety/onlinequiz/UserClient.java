package com.fekrety.onlinequiz;

import android.app.Application;

import  com.fekrety.onlinequiz.model.User;


public class UserClient extends Application {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
