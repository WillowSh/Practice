package com.example.startpractice;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.controller.QNRController;
import com.example.startpractice.dao.entity.QNREntity;
import jakarta.annotation.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.Date;

@SpringBootTest
public class QNRTest {
    @Resource
    private QNRController qnrController;
    String resource = "mybatis-config.xml";
    Logger log = Logger.getLogger(QNRTest.class);

    @Test
    public void queryQNRList() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*编写测试用例*/
        /*成功*/
        QNREntity qnrEntity = new QNREntity();
        qnrEntity.setProjectId("2");
        /*失败*/
        QNREntity qnrEntity_2 = new QNREntity();
        qnrEntity_2  .setProjectId("noId");

        HttpResponseEntity httpResponseEntity = qnrController.queryQNRList(qnrEntity);
        HttpResponseEntity httpResponseEntity_2 = qnrController.queryQNRList(qnrEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryQNRList问卷列表查询测试成功");
        }
        
    }

    @Test
    public void queryQNR() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        QNREntity qnrEntity = new QNREntity();
        qnrEntity.setId("QNR16877416681540j75etaxe");
        /*失败*/
        QNREntity qnrEntity_2 = new QNREntity();
        qnrEntity_2.setId("noId");

        HttpResponseEntity httpResponseEntity = qnrController.queryQNR(qnrEntity);
        HttpResponseEntity httpResponseEntity_2 = qnrController.queryQNR(qnrEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryQNR问卷查询测试成功");
        }
    }

    @Test
    public void addQNRInfo() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        QNREntity qnrEntity = new QNREntity();
        qnrEntity.setId("QNR16877416681540j75e_new");
        /*失败*/
        /*QNREntity qnrEntity_2 = new QNREntity();
        qnrEntity.setId("");*/

        HttpResponseEntity httpResponseEntity = qnrController.addQNRInfo(qnrEntity);
       /* HttpResponseEntity httpResponseEntity_2 = qnrController.addQNRInfo(qnrEntity_2);*/

       /* System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());*/
        /*if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0")) */
        if(httpResponseEntity.getCode().equals("666"))
        {
            log.info(">>addQNRInfo问卷添加测试成功");
        }
    }

    @Test
    public void deleteQNRById() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        QNREntity qnrEntity = new QNREntity();
        qnrEntity.setId("QNR16877416681540j75e_new");
        qnrEntity.setEndDate(new Date(2023, 5, 12,11,32,52));
        /*失败*/
        QNREntity qnrEntity_2 = new QNREntity();
        qnrEntity_2.setId("noId");

        HttpResponseEntity httpResponseEntity = qnrController.deleteQNRById(qnrEntity);
        HttpResponseEntity httpResponseEntity_2 = qnrController.deleteQNRById(qnrEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>deleteQNRById问卷删除测试成功");
        }
    }

}
