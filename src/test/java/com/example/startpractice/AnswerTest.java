package com.example.startpractice;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.controller.AnswerController;
import com.example.startpractice.controller.OptionController;
import com.example.startpractice.dao.entity.AnswerEntity;
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
public class AnswerTest {
    @Resource
    private AnswerController answerController;
    String resource = "mybatis-config.xml";
    Logger log = Logger.getLogger(AnswerTest.class);

    @Test
    public void queryAnswerList() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*编写测试用例*/
        /*成功*/
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setQuestionId("ques1687674161356105codnk8");
        answerEntity.setAnswer("A.是");
        answerEntity.setAsId("1");
        /*失败*/
        AnswerEntity answerEntity_2 = new AnswerEntity();
        answerEntity_2.setQuestionId("noId");
        answerEntity_2.setAnswer("noAnswer");
        answerEntity_2.setAsId("noAsId");

        HttpResponseEntity httpResponseEntity = answerController.queryAnswerList(answerEntity);
        HttpResponseEntity httpResponseEntity_2 = answerController.queryAnswerList(answerEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryAnswerList答案列表查询测试成功");
        }

    }

    @Test
    public void queryAnswerList2() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setQuestionId("ques1687674175899g9lx5nph5");
        answerEntity.setAsId("1");
        /*失败*/
        AnswerEntity answerEntity_2 = new AnswerEntity();
        answerEntity_2.setQuestionId("noId");
        answerEntity_2.setAsId("noAsId");

        HttpResponseEntity httpResponseEntity = answerController.queryAnswerList2(answerEntity);
        HttpResponseEntity httpResponseEntity_2 = answerController.queryAnswerList2(answerEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryAnswerList2答案查询测试成功");
        }
    }

    @Test
    public void addAnswerInfo() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setQuestionId("ques1687799250998zo4je2o0n");
        answerEntity.setAsId("1");
        answerEntity.setAnswer("1//left:A");
        /*失败*/
        /*AnswerEntity answerEntity_2 = new AnswerEntity();
        answerEntity.setId("");*/

        HttpResponseEntity httpResponseEntity = answerController.addAnswerInfo(answerEntity);
        /* HttpResponseEntity httpResponseEntity_2 = answerController.addAnswerInfo(answerEntity_2);*/

        /* System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());*/
        /*if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0")) */
        if(httpResponseEntity.getCode().equals("666"))
        {
            log.info(">>addAnswerInfo答案添加测试成功");
        }
    }

    @Test
    public void queryAnswerForStat() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setQuestionId("ques1687674175899g9lx5nph5");
        answerEntity.setAnswer("B.mul2");
        /*失败*/
        AnswerEntity answerEntity_2 = new AnswerEntity();
        answerEntity_2.setQuestionId("noId");
        answerEntity_2.setAnswer("noAnswer");

        HttpResponseEntity httpResponseEntity = answerController.queryAnswerForStat(answerEntity);
        HttpResponseEntity httpResponseEntity_2 = answerController.queryAnswerForStat(answerEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryAnswer答案查询测试成功");
        }
    }

}
