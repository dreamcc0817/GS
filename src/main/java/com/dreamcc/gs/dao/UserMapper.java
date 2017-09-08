package com.dreamcc.gs.dao;

import org.apache.ibatis.annotations.Param;

import com.dreamcc.gs.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    // =======================================
    //登录名登录
    int checkUsername(String username);
    //邮箱登录
    int checkEmail(String email);
    
    User selectLogin(@Param("email") String username,@Param("pwd") String password);
    
    int updatePasswordByUsername(@Param("email")String username,@Param("pwd")String passwordNew);


}