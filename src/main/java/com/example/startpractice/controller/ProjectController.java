package com.example.startpractice.controller;

import com.example.startpractice.beans.HttpResponseEntity;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProjectController {

    @Autowired
    public ProjectService projectService;


    //用户添加
    @PostMapping(value = "/addProjectInfo",headers = "Accept=application/json")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=projectService.addProjectInfo(projectEntity);
            if(result!=0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            }else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
            System.out.println(projectEntity.toString());
            System.out.println("返回结果：" + result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    //项目搜索
    @PostMapping(value = "/selectProjectInfo",headers = "Accept=application/json")
    public HttpResponseEntity selectProjectInfo(@RequestBody ProjectEntity projectEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<ProjectEntity> hasProject=projectService.selectProjectInfo(projectEntity);
            if(CollectionUtils.isEmpty(hasProject)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasProject);
                httpResponseEntity.setMessage("没找到此项目");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasProject.get(0));
                httpResponseEntity.setMessage("成功");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }



    //项目列表查询
    @PostMapping(value = "/queryProjectList",headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity){

        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<ProjectEntity> hasProject=projectService.queryProjectList(projectEntity);
            if(CollectionUtils.isEmpty(hasProject)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasProject);
                httpResponseEntity.setMessage("无项目信息");
            }else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasProject);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }


    //用户修改
    @PostMapping(value = "/modifyProjectInfo",headers = "Accept=application/json")
    public HttpResponseEntity modifyProjectInfo(ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();

            int result=projectService.modifyProjectInfo(projectEntity);
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

    //用户删除
    @PostMapping(value = "/deleteProjectById",headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById(ProjectEntity projectEntity){

        System.out.println(projectEntity);
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int result=projectService.deleteProjectById(projectEntity);
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
