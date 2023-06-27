package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import com.example.startpractice.dao.entity.UserEntity;
import com.example.startpractice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping(value = "/addQuestionInfo",headers = "Accept=application/json")
    public HttpResponseEntity addQuestionInfo(@RequestBody QuestionEntity questionEntity){

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
    @PostMapping(value = "/deleteQuestionById",headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionById(@RequestBody QuestionEntity questionEntity){

        System.out.println(questionEntity);
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=questionService.deleteQuestionById(questionEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @PostMapping(value = "/modifyQuestionInfo",headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionInfo(@RequestBody QuestionEntity questionEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();

        int result=questionService.modifyQuestionInfo(questionEntity);
        if(result!=0){
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(result);
            httpResponseEntity.setMessage("修改成功");
        }else {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("修改失败");
        }
        return httpResponseEntity;
    }
    @RequestMapping(value = "/queryQuestionList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionList(@RequestBody QuestionEntity questionEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<QuestionEntity> hasQuestion=questionService.queryQuestionList(questionEntity);
            if(CollectionUtils.isEmpty(hasQuestion)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQuestion);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionListForStat",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionListForStat(@RequestBody QuestionEntity questionEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<QuestionEntity> hasQuestion=questionService.queryQuestionListForStat(questionEntity);
            if(CollectionUtils.isEmpty(hasQuestion)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQuestion);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
