package com.example.startpractice;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.controller.ProjectController;
import com.example.startpractice.dao.ProjectEntityMapper;
import com.example.startpractice.dao.UserEntityMapper;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.dao.entity.UserEntity;
import jakarta.annotation.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectTest {
    @Resource
    private ProjectController projectController;
    Logger log = Logger.getLogger(String.valueOf(UserTest.class));
    @Test
    public void queryProjectList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        /*
         * 项目查询成功
         * */
        ProjectEntity projectEntity_1 = new ProjectEntity();
        projectEntity_1.setCreatedBy("admin");

        ProjectEntity projectEntity_2 = new ProjectEntity();
        projectEntity_2.setProjectName("project2");

        /*
         * 项目查询失败
         * */
        ProjectEntity projectEntity_3 = new ProjectEntity();
        projectEntity_3.setCreatedBy("none");

        ProjectEntity projectEntity_4 = new ProjectEntity();
        projectEntity_4.setProjectName("noProject");

        HttpResponseEntity httpResponseEntity_1 = projectController.queryProjectList(projectEntity_1);
        HttpResponseEntity httpResponseEntity_2 = projectController.queryProjectList(projectEntity_2);
        HttpResponseEntity httpResponseEntity_3 = projectController.queryProjectList(projectEntity_3);
        HttpResponseEntity httpResponseEntity_4 = projectController.queryProjectList(projectEntity_4);

        if(httpResponseEntity_1.getCode().equals("666") && httpResponseEntity_2.getCode().equals("666")
                && httpResponseEntity_3.getCode().equals("0") && httpResponseEntity_4.getCode().equals("0"))
        {
            log.info(">>queryProjectList项目列表查询测试成功");
        }
    }

    @Test
    public void projectInsert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        /*
         * 项目新建成功
         * */
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setUserId("1");
        projectEntity.setProjectName("newProject");
        projectEntity.setCreatedBy("admin");
        projectEntity.setLastUpdatedBy("admin");
        projectEntity.setProjectContent("New Project!");

        /*
         * 项目新建失败
         * */
        /*ProjectEntity projectEntity_2 = new ProjectEntity();
        projectEntity.setId("none");*/

        HttpResponseEntity httpResponseEntity = projectController.addProjectInfo(projectEntity);
        /*HttpResponseEntity httpResponseEntity_2 = projectController.addProjectInfo(projectEntity_2);
         */
        if(httpResponseEntity.getCode().equals(666))
        {
            log.info(">>insert项目插入测试成功");
        }
    }

    @Test
    public void deleteProjectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        /*
         * 项目删除成功
         * */
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("4");

        /*
         * 项目删除失败
         * */
        ProjectEntity projectEntity_2 = new ProjectEntity();
        projectEntity_2.setId("noId");

        HttpResponseEntity httpResponseEntity = projectController.deleteProjectById(projectEntity);
        HttpResponseEntity httpResponseEntity_2 = projectController.deleteProjectById(projectEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>delete项目删除测试成功");
        }
    }

    @Test
    public void modifyProjectInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);

        ProjectEntity projectEntity = new ProjectEntity();
        /*
         * 项目修改成功
         */
        projectEntity.setId("1");
        projectEntity.setUserId("1");
        projectEntity.setCreatedBy("admin");
        projectEntity.setProjectName("project1_mod");
        projectEntity.setProjectContent("Project1 is modified!");

        long now = System.currentTimeMillis();
        Date date = new Date();
        date.setTime(now);
        projectEntity.setLastUpdateDate(date);
        projectEntity.setLastUpdatedBy("admin2");

        /*
         * 项目修改失败
         * */
        ProjectEntity projectEntity_2 = new ProjectEntity();
        projectEntity_2.setId("noId");

        HttpResponseEntity httpResponseEntity = projectController.modifyProjectInfo(projectEntity);
        HttpResponseEntity httpResponseEntity_2 = projectController.modifyProjectInfo(projectEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>update项目修改测试成功");
        }
    }

}
