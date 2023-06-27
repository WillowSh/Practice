package com.example.startpractice;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.controller.OptionController;
import com.example.startpractice.controller.QNRController;
import com.example.startpractice.controller.QuestionController;
import com.example.startpractice.dao.entity.OptionEntity;
import com.example.startpractice.dao.entity.QNREntity;
import com.example.startpractice.dao.entity.QuestionEntity;
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
public class OptionTest {
    @Resource
    private OptionController optionController;
    String resource = "mybatis-config.xml";
    Logger log = Logger.getLogger(QNRTest.class);

    @Test
    public void queryOptionList() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*编写测试用例*/
        /*成功*/
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setQuestionId("ques1687674175899g9lx5nph5");
        /*失败*/
        OptionEntity optionEntity_2 = new OptionEntity();
        optionEntity_2  .setQuestionId("noId");

        HttpResponseEntity httpResponseEntity = optionController.queryOptionList(optionEntity);
        HttpResponseEntity httpResponseEntity_2 = optionController.queryOptionList(optionEntity_2);

        System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());
        if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0"))
        {
            log.info(">>queryOptionList选项列表查询测试成功");
        }

    }

    @Test
    public void addOptionInfo() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*编写测试用例*/
        /*成功*/
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setOptionContent("选项测试");
        /*失败*/
        /*OptionEntity optionEntity_2 = new OptionEntity();
        optionEntity.setId("");*/

        HttpResponseEntity httpResponseEntity = optionController.addOptionInfo(optionEntity);
        /* HttpResponseEntity httpResponseEntity_2 = optionController.addOptionInfo(optionEntity_2);*/

        /* System.out.println(httpResponseEntity.getCode()+httpResponseEntity_2.getCode());*/
        /*if(httpResponseEntity.getCode().equals("666") && httpResponseEntity_2.getCode().equals("0")) */
        if(httpResponseEntity.getCode().equals("666"))
        {
            log.info(">>addOptionInfo选项添加测试成功");
        }
    }
}
