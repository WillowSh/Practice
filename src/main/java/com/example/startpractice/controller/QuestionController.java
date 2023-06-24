package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import com.example.startpractice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping(value = "/addQuestionInfo",headers = "Accept=application/json")
    public HttpResponseEntity addQuestionInfo(QuestionEntity questionEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=questionService.addQuestionInfo(questionEntity);

            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
            System.out.println(questionEntity);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

}
