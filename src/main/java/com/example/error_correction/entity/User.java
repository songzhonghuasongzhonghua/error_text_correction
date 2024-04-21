package com.example.error_correction.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;


public class User {
    @TableId
    private int id;
    private String username;
    private String password;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date   birth;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", createAt=" + createAt +
                ", birth=" + birth +
                ", type=" + type +
                '}';
    }



    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    private int type; //0-普通用户 1-管理员

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreateAt(java.util.Date  createAt) {
        this.createAt = createAt;
    }

    public void setBirth(java.util.Date  birth) {
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public java.util.Date  getCreateAt() {

        return createAt;
    }

    public User( String username, String password, String phone, java.util.Date createAt, java.util.Date birth, int type) {

        this.username = username;
        this.password = password;
        this.phone = phone;
        this.createAt = createAt;
        this.birth = birth;
        this.type = type;
    }

    public User(){}

    public java.util.Date getBirth() {
        return birth;
    }




}


