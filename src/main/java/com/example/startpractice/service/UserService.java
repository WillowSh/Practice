package com.example.startpractice.service;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.entity.UserEntity;
import com.example.startpractice.dao.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserEntityMapper userEntityMapper;


    //登录
    public List<UserEntity> selectUserInfo(UserEntity userEntity){

        List<UserEntity> result=userEntityMapper.selectUserInfo(userEntity);
        return result;
    }
    //查询用户列表
    public List<UserEntity> queryUserList(UserEntity userEntity){

        List<UserEntity> result=userEntityMapper.queryUserList(userEntity);
        return result;
    }

    //创建用户
    public int addUserInfo(UserEntity userEntity){

        userEntity.setId(UUIDUtil.getOneUUID());
        int userResult=userEntityMapper.insert(userEntity);

        return userResult;
    }

    //修改用户
    public int modifyUserInfo(UserEntity userEntity){

        int userResult=userEntityMapper.modifyUserInfo(userEntity);
        return userResult;
    }

    //删除用户
    public int deleteUserById(UserEntity userEntity){
        int userResult=userEntityMapper.deleteUserById(userEntity);
        return userResult;
    }

    ////为JMeter测试而创造的方法
    public int deleteUserByName(UserEntity userEntity){
        int userResult=userEntityMapper.deleteUserByName(userEntity);
        return userResult;
    }

}
