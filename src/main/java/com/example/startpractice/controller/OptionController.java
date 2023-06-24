package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.OptionEntity;
import com.example.startpractice.dao.entity.QNREntity;
import com.example.startpractice.service.QNRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OptionController {

    @PostMapping(value = "/addOptionInfo",headers = "Accept=application/json")
    public HttpResponseEntity addQNRInfo(@RequestBody OptionEntity options) {

        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        System.out.println(options);

        httpResponseEntity.setCode("666");
        httpResponseEntity.setData(1);
        httpResponseEntity.setMessage("修改成功");
        return httpResponseEntity;
    }
}
