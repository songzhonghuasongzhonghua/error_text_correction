package com.example.error_correction.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.error_correction.entity.Text;
import com.example.error_correction.entity.User;
import com.example.error_correction.entity.response.ListRes;
import com.example.error_correction.mapper.TextMapper;
import com.example.error_correction.mapper.UserMapper;
import com.example.error_correction.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@RequestMapping("/admin")
@RestController
public class AdminController  {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TextMapper textMapper;


    //获取普通用户列表或管理员列表
    @GetMapping("/user_list")
    public Result user_list(@RequestParam(required = true) int page,
                            @RequestParam(required = true) int pageSize,
                            @RequestParam(required = true) int type,
                            @RequestParam(required = false) Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //筛选普通用户列表
        queryWrapper.eq("type", type);
        if(id != null) {
            //搜索id
            queryWrapper.eq("id", id);
        }
        Page<User> userPage = new Page<>(page,pageSize);
        Page<User>  userList = userMapper.selectPage(userPage,queryWrapper);
        return Result.success().data("info",userList);
    }

    //删除普通用户或管理员
    @GetMapping("/user_del")
    public Result user_del(@RequestParam(required = true) int id) {
         int delId =  userMapper.deleteById(id);
         if(delId  == 0) {
             return Result.failed().message("删除失败或用户不存在");
         }
         return Result.success();
    }


    //新增普通用户或管理员
    @PostMapping("/user")
    public Result user_add(@RequestBody User user) {
        User userFromDB = userMapper.findByUsername(user.getUsername());
        if(userFromDB != null) {
            return Result.failed().message("用户名已存在");
        }
        Date dateTime = new java.util.Date();
        user.setCreateAt(dateTime);
      userMapper.insert(user);
      return Result.success();
    }




    //获取文本列表
    @GetMapping("/text_list")
    public Result text_list(@RequestParam(required = true) int page,
                            @RequestParam(required = true) int pageSize,
                            @RequestParam(required = false) Integer listId,
                            @RequestParam(required = false) Integer userId){


        QueryWrapper<Text> queryWrapper = new QueryWrapper<>();
        if(listId != null) {
            queryWrapper.eq("id", listId);
        }
        if(userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        Page<Text> textPage = new Page<>(page,pageSize);
        Page<Text> textList = textMapper.selectPage(textPage,queryWrapper,listId,userId);
        return Result.success().data("info",textList);
    }


    //删除文本
    @GetMapping("/text_del")
    public Result text_del(@RequestParam(required = true) int id) {
        Text textFromDB = textMapper.selectById(id);
        if(textFromDB == null) {
            return Result.failed().message("文本记录不存在");
        }
        int delId = textMapper.deleteById(id);
        if(delId == 0) {
            return Result.failed().message("删除失败");
        }else{
            return Result.success();
        }
    }
}
