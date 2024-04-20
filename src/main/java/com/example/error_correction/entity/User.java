package com.example.error_correction.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class User {
    @TableId
    private int id;
    private String username;
    private String password;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime createAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime  birth;


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

    public void setCreateAt(LocalDateTime  createAt) {
        this.createAt = createAt;
    }

    public void setBirth(LocalDateTime  birth) {
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

    public OffsetDateTime  getCreateAt() {
        if(this.createAt == null) {
            return null;
        }
        return createAt.atOffset(ZoneOffset.UTC);
    }

    public User( String username, String password, String phone, LocalDateTime createAt, LocalDateTime birth, int type) {

        this.username = username;
        this.password = password;
        this.phone = phone;
        this.createAt = createAt;
        this.birth = birth;
        this.type = type;
    }

    public User(){}

    public OffsetDateTime getBirth() {
        if(this.birth == null) {
            return null;
        }
        return birth.atOffset(ZoneOffset.UTC);
    }




}


