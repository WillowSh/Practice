package com.example.startpractice.serviceTest;

import com.example.startpractice.dao.UserEntityMapper;
import com.example.startpractice.dao.entity.UserEntity;
import com.example.startpractice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityMapper userEntityMapper;

    @Test
    public void testSelectUserInfo() throws Exception {
        UserEntity userEntity = new UserEntity();
        // 设置 userEntity 的属性

        List<UserEntity> userList = new ArrayList<>();
        // 向 userList 添加用户实体对象

        when(userEntityMapper.selectUserInfo(eq(userEntity))).thenReturn(userList);

        List<UserEntity> result = userService.selectUserInfo(userEntity);
        assertEquals(userList, result);
    }



    @Test
    public void testAddUserInfo() throws Exception {
        UserEntity userEntity = new UserEntity();
        // 设置 userEntity 的属性
        userEntity.setStopTime(new Date());
        userEntity.setStartTime(new Date());

        when(userEntityMapper.insert(eq(userEntity))).thenReturn(1);

        int result = userService.addUserInfo(userEntity);
        assertEquals(1, result);

    }

    @Test
    public void testModifyUserInfo() throws Exception {
        UserEntity userEntity = new UserEntity();
        // 设置 userEntity 的属性

        when(userEntityMapper.modifyUserInfo(eq(userEntity))).thenReturn(1);

        int result = userService.modifyUserInfo(userEntity);
        assertEquals(1, result);

    }

    @Test
    public void testDeleteUserById() throws Exception {
        UserEntity userEntity=new UserEntity();
        String userId = "1";
        userEntity.setId(userId);
        when(userEntityMapper.deleteUserById(eq(userEntity))).thenReturn(1);

        int result = userService.deleteUserById(userEntity);
        assertEquals(1, result);
    }


}
