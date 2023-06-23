package com.example.startpractice.entityTest;

import com.example.startpractice.dao.UserEntityMapper;
import com.example.startpractice.dao.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserEntityMapperTest {
    @Autowired
    private UserEntityMapper userEntityMapper;

    @Test
    public void testQueryUserList() {
        UserEntity userEntity = new UserEntity();
        // 设置条件参数
        userEntity.setUsername("js");

        List<UserEntity> userList = userEntityMapper.queryUserList(userEntity);

        Assertions.assertNotNull(userList);
        // 其他断言验证...
    }


    @Test
    @Transactional
    public void testInsert() {
        UserEntity userEntity = new UserEntity();
        // 设置用户属性
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setUsername("js111");
        userEntity.setPassword("123");
        userEntity.setStartTime(new Date());
        userEntity.setStopTime(new Date());
        userEntity.setCreationDate(new Date());
        userEntity.setCreatedBy("js111");

        int result = userEntityMapper.insert(userEntity);

        Assertions.assertNotEquals(0, result);
        // 其他断言验证...
    }

    @Test
    @Transactional
    public void testDeleteUserById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        int result = userEntityMapper.deleteUserById(userEntity);
        Assertions.assertNotEquals(0, result);
        // 其他断言验证...
    }
    @Test
    @Transactional
    public void testUpdateByPrimaryKeySelective() {
        UserEntity userEntity = new UserEntity();
        // 设置用户属性
        userEntity.setId("1");
        userEntity.setUsername("js111");
        userEntity.setPassword("123");
        userEntity.setStartTime(new Date());
        userEntity.setStopTime(new Date());

        int result = userEntityMapper.modifyUserInfo(userEntity);

        Assertions.assertNotEquals(0, result);
        // 其他断言验证...
    }

    @Test
    public void testSelectUserInfo() {
        UserEntity userEntity = new UserEntity();
        // 设置用户属性
        userEntity.setUsername("admin_J");
        userEntity.setPassword("123");

        List<UserEntity> userInfoList = userEntityMapper.selectUserInfo(userEntity);

        Assertions.assertNotNull(userInfoList);
        // 其他断言验证...
    }

}
