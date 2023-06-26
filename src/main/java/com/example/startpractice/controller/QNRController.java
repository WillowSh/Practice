package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.QNREntity;
import com.example.startpractice.dao.entity.UserEntity;
import com.example.startpractice.service.QNRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QNRController {
    @Autowired
    private QNRService qnrService;

    @PostMapping(value = "/addQNRInfo",headers = "Accept=application/json")
    public HttpResponseEntity addQNRInfo(@RequestBody QNREntity qnrEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=qnrService.addQNRInfo(qnrEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
            System.out.println(qnrEntity);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @PostMapping(value = "/queryQNRList",headers = "Accept=application/json")
    public HttpResponseEntity queryQNRList(@RequestBody QNREntity qnrEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<QNREntity> hasQNR=qnrService.queryQNRList(qnrEntity);
            if(CollectionUtils.isEmpty(hasQNR)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasQNR);
                httpResponseEntity.setMessage("无项目信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQNR);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    @PostMapping(value = "/queryQNR",headers = "Accept=application/json")
    public HttpResponseEntity queryQNR(@RequestBody QNREntity qnrEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<QNREntity> hasQNR=qnrService.queryQNR(qnrEntity);
            if(CollectionUtils.isEmpty(hasQNR)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasQNR);
                httpResponseEntity.setMessage("无项目信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQNR);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }


    @RequestMapping(value = "/deleteQNRById",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity deleteUserById(@RequestBody QNREntity qnrEntity){

        System.out.println(qnrEntity);
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=qnrService.deleteQNRById(qnrEntity);
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


}
