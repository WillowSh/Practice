package com.example.startpractice;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.controller.UserController;
import com.example.startpractice.dao.entity.UserEntity;
import jakarta.annotation.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@SpringBootTest
public class UserTest {
    @Resource
    private UserController userController;


    Logger log = Logger.getLogger(String.valueOf(UserTest.class));
    @Test
    public void queryUserList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*
         **用户存在，查询成功
         */
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setUsername("admin");
        /*
         **用户不存在，查询失败
         */
        UserEntity userEntity_2 = new UserEntity();
        userEntity_2.setId("000");
        userEntity_2.setUsername("none");
        /*UserController userController = new UserController();*/

        /*List<UserEntity> list = userEntity.queryUserList(userEntity);*/
        /*if(CollectionUtils.isEmpty(list)){
        }else{
            System.out.println(list);
            log.info(">>queryUserList用户列表查询测试成功");
        }*/
        HttpResponseEntity httpResponseEntity = userController.queryUserList(userEntity);
        HttpResponseEntity httpResponseEntity_2 = userController.queryUserList(userEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals(0))
        {
            log.info(">>queryUserList用户列表查询测试成功");
        }
    }

    @Test
    public void selectUserInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        /*
         **用户存在，登录成功
         */
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setUsername("admin");
        userEntity.setPassword("111");
        /*
         **用户存在，登录失败
         */
        UserEntity userEntity_2 = new UserEntity();
        userEntity_2.setId("000");
        userEntity_2.setUsername("none");


        HttpResponseEntity httpResponseEntity = userController.userLogin(userEntity);
        HttpResponseEntity httpResponseEntity_2 = userController.userLogin(userEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals(0))
        {
            log.info(">>selectUserInfo用户登录测试成功");
        }
    }

    @Test
    public void insert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        /*
         ** 用户信息设置正确，添加成功
         */
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        userEntity.setUsername("newUser");
        userEntity.setPassword("123");
        /*
         ** 用户信息设置错误，添加失败
         */
       /* UserEntity userEntity_2 = new UserEntity();
        userEntity.setStatus("1");*/

        HttpResponseEntity httpResponseEntity = userController.addUserInfo(userEntity);
        /*HttpResponseEntity httpResponseEntity_2 = userController.addUser(userEntity_2);
         */
        if(httpResponseEntity.getCode().equals("666"))
        {
            log.info(">>insert用户插入测试成功");
        }
    }

    @Test
    public void deleteUserById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        /*
         * 用户删除成功
         * */
        UserEntity userEntity = new UserEntity();
        userEntity.setId("4");
        /*
         * 用户删除失败
         * */
        UserEntity userEntity_2 = new UserEntity();
        userEntity_2.setId("000");


        HttpResponseEntity httpResponseEntity = userController.deleteUserById(userEntity);
        HttpResponseEntity httpResponseEntity_2 = userController.deleteUserById(userEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>delete用户删除测试成功");
        }

    }

    @Test
    public void modifyUserId() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*
         * 用户修改成功
         * */
        UserEntity userEntity = new UserEntity();
        userEntity.setId("2");
        userEntity.setUsername("admin2");
        userEntity.setPassword("newPassword");
        userEntity.setStatus("1");
        /*
         * 用户修改失败
         * */
        UserEntity userEntity_2 = new UserEntity();
        userEntity_2.setId("000");


        HttpResponseEntity httpResponseEntity = userController.modifyUserInfo(userEntity);
        HttpResponseEntity httpResponseEntity_2 = userController.modifyUserInfo(userEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>delete用户删除测试成功");
        }

    }
}
