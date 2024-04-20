package com.example.error_correction.entity.request;

public class ErrorCorrectionRequest {
    private int user_id;
    private int language;
    private int type;
    private String Content;

    @Override
    public String toString() {
        return "ErrorCorrectionRequest{" +
                "user_id=" + user_id +
                ", language=" + language +
                ", type=" + type +
                ", Content='" + Content + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
