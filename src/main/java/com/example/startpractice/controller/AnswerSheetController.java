package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.AnswerSheetEntity;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.service.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerSheetController {
    @Autowired
    public AnswerSheetService answerSheetService;

    @RequestMapping(value = "/queryAnswerSheetList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryAnswerSheetList(@RequestBody AnswerSheetEntity answerSheetEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<AnswerSheetEntity> hasAnswerSheet=answerSheetService.queryAnswerSheetList(answerSheetEntity);
            if(CollectionUtils.isEmpty(hasAnswerSheet)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasAnswerSheet);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryASForStat",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryASForStat(@RequestBody AnswerSheetEntity answerSheetEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result =answerSheetService.queryASForStat(answerSheetEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("查询成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("查询失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryAnswerSheet",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryAnswerSheet(@RequestBody AnswerSheetEntity answerSheetEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<AnswerSheetEntity> hasAnswerSheet=answerSheetService.queryAnswerSheet(answerSheetEntity);
            if(CollectionUtils.isEmpty(hasAnswerSheet)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasAnswerSheet);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @PostMapping(value = "/addAnswerSheetInfo",headers = "Accept=application/json")
    public HttpResponseEntity addAnswerSheetInfo(@RequestBody AnswerSheetEntity answerSheetEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=answerSheetService.addAnswerSheetInfo(answerSheetEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
            System.out.println(answerSheetEntity.toString());
            System.out.println("返回结果：" + result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

}
