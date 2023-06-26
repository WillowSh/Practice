package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.AnswerEntity;
import com.example.startpractice.dao.entity.AnswerSheetEntity;
import com.example.startpractice.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {
    @Autowired
    public AnswerService answerService;

    @RequestMapping(value = "/queryAnswerList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryAnswerList(@RequestBody AnswerEntity answerEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<AnswerEntity> hasAnswer=answerService.queryAnswerList(answerEntity);
            if(CollectionUtils.isEmpty(hasAnswer)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasAnswer);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    @RequestMapping(value = "/queryAnswerList2",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryAnswerList2(@RequestBody AnswerEntity answerEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<AnswerEntity> hasAnswer=answerService.queryAnswerList2(answerEntity);
            if(CollectionUtils.isEmpty(hasAnswer)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasAnswer);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @PostMapping(value = "/addAnswerInfo",headers = "Accept=application/json")
    public HttpResponseEntity addAnswerInfo(@RequestBody AnswerEntity answerEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=answerService.addAnswerInfo(answerEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
            System.out.println(answerEntity.toString());
            System.out.println("返回结果：" + result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }


}
