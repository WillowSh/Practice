package com.example.startpractice;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.controller.AnswerController;
import com.example.startpractice.controller.AnswerSheetController;
import com.example.startpractice.dao.entity.AnswerSheetEntity;
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

@SpringBootTest
public class AnswerSheetTest {
    @Resource
    private AnswerSheetController answerSheetController;
    String resource = "mybatis-config.xml";
    Logger log = Logger.getLogger(AnswerTest.class);

    @Test
    public void queryAnswerSheetList() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*编写测试用例*/
        /*成功*/
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        answerSheetEntity.setqNRId("QNR1687673721182all8ozuct");
        /*失败*/
        AnswerSheetEntity answerSheetEntity_2 = new AnswerSheetEntity();
        answerSheetEntity_2  .setqNRId("noId");

        HttpResponseEntity httpResponseEntity = answerSheetController.queryAnswerSheetList(answerSheetEntity);
        HttpResponseEntity httpResponseEntity_2 = answerSheetController.queryAnswerSheetList(answerSheetEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryAnswerSheetList问卷列表查询测试成功");
        }

    }

    @Test
    public void queryAnswerSheet() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        answerSheetEntity.setId("1");
        /*失败*/
        AnswerSheetEntity answerSheetEntity_2 = new AnswerSheetEntity();
        answerSheetEntity_2.setId("noId");

        HttpResponseEntity httpResponseEntity = answerSheetController.queryAnswerSheet(answerSheetEntity);
        HttpResponseEntity httpResponseEntity_2 = answerSheetController.queryAnswerSheet(answerSheetEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryAnswerSheet问卷查询测试成功");
        }
    }

    @Test
    public void addAnswerSheetInfo() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        answerSheetEntity.setId("2");
        answerSheetEntity.setRespondent("answerSheet测试");
        answerSheetEntity.setqNRId("QNR16877416681540j75etaxe");
        /*失败*/
        /*AnswerSheetEntity answerSheetEntity_2 = new AnswerSheetEntity();
        answerSheetEntity.setId("");*/

        HttpResponseEntity httpResponseEntity = answerSheetController.addAnswerSheetInfo(answerSheetEntity);
        /* HttpResponseEntity httpResponseEntity_2 = answerSheetController.addAnswerSheetInfo(answerSheetEntity_2);*/

        /* System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());*/
        /*if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0")) */
        if(httpResponseEntity.getCode().equals("666"))
        {
            log.info(">>addAnswerSheetInfo问卷添加测试成功");
        }
    }



}
