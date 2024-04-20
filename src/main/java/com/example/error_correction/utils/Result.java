package com.example.error_correction.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    private Result(){}

    public static Result success(){
        Result r = new Result();
        r.setCode(0);
        r.setMessage("成功");
        return r;
    }



    public static  Result  failed(){
        Result r = new Result();
        r.setCode(200);
        r.setData(null);
        return r;
    }


    public Result data(String key,Object value){
        this.data.put(key, value);
        return this;
    }

    public Result message(String message){
        this.message = message;
        return this;
    }





}