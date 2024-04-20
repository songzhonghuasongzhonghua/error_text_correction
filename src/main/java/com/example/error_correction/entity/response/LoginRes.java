package com.example.error_correction.entity.response;

import com.example.error_correction.entity.User;

public class LoginRes {
    private String token;
    private User user;

    @Override
    public String toString() {
        return "LoginRes{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
