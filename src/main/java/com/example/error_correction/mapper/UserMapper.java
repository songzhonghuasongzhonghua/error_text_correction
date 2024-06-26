package com.example.error_correction.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.error_correction.entity.Text;
import com.example.error_correction.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user")
    public List<User> find();


    //通过名称搜索用户
    @Select("select * from user where username = #{username}")
    public User findByUsername(String username);



}