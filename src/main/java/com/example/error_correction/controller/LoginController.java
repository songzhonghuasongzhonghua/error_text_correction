package com.example.error_correction.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.error_correction.entity.User;
import com.example.error_correction.entity.response.LoginRes;
import com.example.error_correction.mapper.UserMapper;
import com.example.error_correction.utils.JwtUtil;
import com.example.error_correction.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;
//登录接口
    @GetMapping("/login")
    public Result login(String username, String password){
        User user = userMapper.findByUsername(username);
       //用户不存在
        if(user == null){
            System.out.println(user);
            return Result.failed().message("用户不存在");
        }else {
            if(user.getPassword().equals(password)){
                String token =  JwtUtil.generateToken(username);
                LoginRes loginRes = new LoginRes();
                loginRes.setToken(token);
                loginRes.setUser(user);
                return Result.success().data("info", loginRes);
            }else {
                return Result.failed().message("密码不正确");
            }
        }
    }


//    注册接口
    @GetMapping("/register")
    public Result sign(String username, String password, String phone){
        User userFromDB = userMapper.findByUsername(username);
        if(userFromDB == null){
            LocalDateTime dateTime = LocalDateTime.now();
            User user = new User(username,password,phone,dateTime,null,0);
            userMapper.insert(user);
            return Result.success().data("user",user);
        }else {
            return Result.failed().message("人员已存在");
        }

    }

//重置密码接口
    @GetMapping("/reset_pd")
    public Result resetpassword(String username, String password,String phone){
        User userFromDB = userMapper.findByUsername(username);
        System.out.println(userFromDB == null);
        if(userFromDB == null){
            return Result.failed().message("人员不存在");
        }else {
            //判断手机号是否相同
            if(!userFromDB.getPhone().equals(phone)){
                return  Result.failed().message("手机号错误");
            }

            //判断和数据库中的密码是否相同
            if(userFromDB.getPassword().equals(password)){
                return Result.failed().message("密码相同");
            }

            // 创建UpdateWrapper对象，设置更新条件
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username", username);
            User user = new User();
            user.setPassword(password);
            userMapper.update(user,updateWrapper);
            return Result.success();
        }

    }
}
