package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.OptionEntity;
import com.example.startpractice.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OptionController {
    @Autowired
    public OptionService optionService;

    @PostMapping(value = "/addOptionInfo",headers = "Accept=application/json")
    public HttpResponseEntity addOptionInfo(@RequestBody OptionEntity optionEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=optionService.addOptionInfo(optionEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
            System.out.println(optionEntity.toString());
            System.out.println("返回结果：" + result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryOptionList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryOptionList(@RequestBody OptionEntity optionEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<OptionEntity> hasOption=optionService.queryOptionList(optionEntity);
            if(CollectionUtils.isEmpty(hasOption)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("无信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasOption);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }


}
