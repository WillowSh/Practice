package com.example.startpractice;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.controller.QNRController;
import com.example.startpractice.controller.QuestionController;
import com.example.startpractice.dao.QuestionEntityMapper;
import com.example.startpractice.dao.entity.QuestionEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import jakarta.annotation.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
public class QuestionTest {
    @Resource
    private QuestionController questionController;
    String resource = "mybatis-config.xml";
    Logger log = Logger.getLogger(QNRTest.class);

    @Test
    public void queryQuestionList() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*
         * 问题查询成功
         * */
        QuestionEntity questionEntity_1 = new QuestionEntity();
        questionEntity_1.setqNRId("QNR1687673721182all8ozuct");

        /*
         * 问题查询失败
         * */
        QuestionEntity questionEntity_2 = new QuestionEntity();
        questionEntity_2.setqNRId("none");

        HttpResponseEntity httpResponseEntity_1 = questionController.queryQuestionList(questionEntity_1);
        HttpResponseEntity httpResponseEntity_2 = questionController.queryQuestionList(questionEntity_2);

        if(httpResponseEntity_1.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryQuestionList问题列表查询测试成功");
        }
    }

    @Test
    public void questionInsert() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*
         * 问题新建成功
         * */
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId("ques16876742894727kqox_new");
        questionEntity.setQuestionType("单选");
        questionEntity.setQuestionContent("newQuestion");
        questionEntity.setqNRId("QNR16877416681540j75etaxe");

        /*
         * 问题新建失败
         * */
        /*QuestionEntity questionEntity_2 = new QuestionEntity();
        questionEntity.setId("none");*/

        HttpResponseEntity httpResponseEntity = questionController.addQuestionInfo(questionEntity);
        /*HttpResponseEntity httpResponseEntity_2 = questionController.addQuestionInfo(questionEntity_2);
         */
        if(httpResponseEntity.getCode().equals(666))
        {
            log.info(">>insert问题插入测试成功");
        }
    }

    @Test
    public void deleteQuestionById() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*
         * 问题删除成功
         * */
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId("ques16876742894727kqox_new");
        /*
         * 问题删除失败
         * */
        QuestionEntity questionEntity_2 = new QuestionEntity();
        questionEntity_2.setId("noId");

        HttpResponseEntity httpResponseEntity = questionController.deleteQuestionById(questionEntity);
        HttpResponseEntity httpResponseEntity_2 = questionController.deleteQuestionById(questionEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>delete问题删除测试成功");
        }
    }

    @Test
    public void modifyQuestionInfo() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        QuestionEntity questionEntity = new QuestionEntity();
        /*
         * 问题修改成功
         */
        questionEntity.setId("ques16876742894727kqadszss");
        questionEntity.setQuestionType("多选");
        questionEntity.setQuestionContent("修改问题");
        questionEntity.setqNRId("QNR16877416681540j75e_new");
        /*
         * 问题修改失败
         * */
        QuestionEntity questionEntity_2 = new QuestionEntity();
        questionEntity_2.setId("noId");

        HttpResponseEntity httpResponseEntity = questionController.modifyQuestionInfo(questionEntity);
        HttpResponseEntity httpResponseEntity_2 = questionController.modifyQuestionInfo(questionEntity_2);

        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>update问题修改测试成功");
        }
    }
}
