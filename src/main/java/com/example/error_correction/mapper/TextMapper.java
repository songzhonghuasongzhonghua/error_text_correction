package com.example.error_correction.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.error_correction.entity.Text;
import com.example.error_correction.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface TextMapper extends BaseMapper<Text> {

    //查询文本列表
    @Select({
            "<script>",
            "select t.*, u.username as user_username from text t",
            "left join user u on t.user_id = u.id",
            "<where>",
            "<if test='id != null'>",
            "and t.id = #{id}",
            "</if>",
            "<if test='userId != null'>",
            "and t.user_id = #{userId}",
            "</if>",
            "<if test='userName != null'>",
            "and u.username like concat('%', #{userName}, '%')",
            "</if>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "language", property = "language"),
            @Result(column = "type", property = "type"),
            @Result(column = "content", property = "content"),
            @Result(column = "result", property = "result"),
            @Result(column = "user_id", property = "user", javaType = User.class,
                    one = @One(select = "com.example.error_correction.mapper.UserMapper.selectById", fetchType = FetchType.LAZY))
    })
    Page<Text> selectPage(Page<Text> page, QueryWrapper<Text> queryWrapper,Integer id,Integer userId,String userName);



//    @Select({
//            "<script>",
//            "select * from text",
//            "<where>",
//            "<if test='id != null'>",
//            "and id = #{id}",
//            "</if>",
//            "<if test='userId != null'>",
//            "and user_id = #{userId}",
//            "</if>",
//            "</where>",
//            "LIMIT #{offset}, #{limit}",
//            "</script>"
//    })
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "language", property = "language"),
//            @Result(column = "type", property = "type"),
//            @Result(column = "content", property = "content"),
//            @Result(column = "result", property = "result"),
//            @Result(column = "user_id", property = "user", javaType = User.class,
//                    one = @One(select = "com.example.error_correction.mapper.UserMapper.selectById", fetchType = FetchType.LAZY))
//    })
//    List<Text> selectTextWithPagination(@Param("id") Integer id, @Param("userId") Integer userId, @Param("offset") int offset, @Param("limit") int limit);



}
