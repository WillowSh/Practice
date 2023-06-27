package com.example.startpractice;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.ProjectEntityMapper;
import com.example.startpractice.dao.UserEntityMapper;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.dao.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectTest {

  //  @Test
    void contextLoads() {
    }

    Logger log = Logger.getLogger(ProjectTest.class);
    @Test
    public void queryUserList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>queryUserList用户列表查询测试成功");
        }
    }

    @Test
    public void selectUserInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin_J");
        userEntity.setPassword("123");
        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);
        if(CollectionUtils.isEmpty(list)){
            // 记录error级别的信息
        }else{
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>selectUserInfo用户登录测试成功");
        }
    }

    @Test
    public void insert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        userEntity.setUsername("LS");
        userEntity.setPassword("123");
        int i = userEntityMapper.insert(userEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert用户插入测试成功");
        }
    }

    @Test
    public void deleteUserById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        int i = userEntityMapper.deleteUserById(userEntity);
        if(i==0){
            // 记录error级别的信息
            System.out.println(i);
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete用户删除测试成功");
        }
    }
    @Test
    public void queryProjectList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("six");
        List<ProjectEntity> list1 = projectEntityMapper.queryProjectList(projectEntity);
        projectEntity.setProjectName(null);
        projectEntity.setCreatedBy("admin_J");
        List<ProjectEntity> list2 = projectEntityMapper.queryProjectList(projectEntity);
        if(CollectionUtils.isEmpty(list1)||CollectionUtils.isEmpty(list2)){
            // 记录error级别的信息
        }else{
            System.out.println(list1);
            // 记录info级别的信息
            log.info(">>queryProjectList项目列表查询测试成功");
        }
    }

    @Test
    public void insertProject() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setProjectName("much");
        projectEntity.setProjectContent("test");
        projectEntity.setCreatedBy("admin_J");
        int i = projectEntityMapper.insert(projectEntity);
        if(i==0){
            // 记录error级别的信息
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert项目插入测试成功");
        }
    }

    @Test
    public void deleteProjectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setCreatedBy("admin_J");
        projectEntity.setId("4a00213e1b234adaa04ee952a6655ea0");
        int i = projectEntityMapper.deleteProjectById(projectEntity);
        if(i==0){
            // 记录error级别的信息
            System.out.println(i);
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete项目删除测试成功");
        }
    }

    @Test
    public void modifyProjectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("4a00213e1b234adaa04ee952a6655ea0");
        projectEntity.setCreatedBy("admin_J");
        projectEntity.setProjectName("modify");
        projectEntity.setProjectContent("modified");
        projectEntity.setLastUpdatedBy("admin_J");
        int i = projectEntityMapper.modifyProjectInfo(projectEntity);
        if(i==0){
            // 记录error级别的信息
            System.out.println(i);
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>modify项目修改测试成功");
        }
    }



}
