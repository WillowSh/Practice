package com.example.startpractice.dao;


import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.dao.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProjectEntityMapper 
{
    //查询项目列表
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);
    //查询项目
    List<ProjectEntity> selectProjectInfo(ProjectEntity projectEntity);

    //创建项目基本信息
    int insert(ProjectEntity projectEntity);

    //根据id删除项目信息
    int deleteProjectById(ProjectEntity projectEntity);

    //编辑项目信息
    int modifyProjectInfo(ProjectEntity projectEntity);
    
    
}
