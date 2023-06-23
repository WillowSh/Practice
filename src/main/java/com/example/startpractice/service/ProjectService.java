package com.example.startpractice.service;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.ProjectEntityMapper;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.dao.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    //搜索
    public List<ProjectEntity> selectProjectInfo(ProjectEntity projectEntity){

        List<ProjectEntity> result=projectEntityMapper.selectProjectInfo(projectEntity);
        return result;
    }

    //查询项目列表
    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){

        List<ProjectEntity> result=projectEntityMapper.queryProjectList(projectEntity);
        return result;
    }

    //创建项目
    public int addProjectInfo(ProjectEntity projectEntity){

        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setCreationDate(new Date());
        int projectResult=projectEntityMapper.insert(projectEntity);

        return projectResult;

    }

    //修改项目
    public int modifyProjectInfo(ProjectEntity projectEntity){

        Date currentTime = new Date();
        projectEntity.setLastUpdateDate(currentTime);
        int projectResult=projectEntityMapper.modifyProjectInfo(projectEntity);
        return projectResult;
    }

    //删除项目
    public int deleteProjectById(ProjectEntity projectEntity){
        int projectResult=projectEntityMapper.deleteProjectById(projectEntity);
        return projectResult;


    }

}
