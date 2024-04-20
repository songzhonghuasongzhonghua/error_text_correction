package com.example.error_correction.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class Text {
    private int id;
    private int language; //0中文 1英文
    private int type;
    private String content;

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", language=" + language +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", result='" + result + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String result;
    private int userId;

    //关联user表
    @TableField(exist = false)
    private User user;



}
