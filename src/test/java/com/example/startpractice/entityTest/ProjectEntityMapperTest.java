package com.example.startpractice.entityTest;

import com.example.startpractice.dao.ProjectEntityMapper;
import com.example.startpractice.dao.entity.ProjectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ProjectEntityMapperTest {

    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    @Test
    public void testQueryProjectList() {
        // 创建条件对象
        ProjectEntity condition = new ProjectEntity();
        // 设置条件参数
        condition.setProjectName("First");


        // 调用接口方法
        List<ProjectEntity> result = projectEntityMapper.queryProjectList(condition);

        // 验证结果
        Assertions.assertNotNull(result);
        // 其他断言验证...


    }

    @Test
    @Transactional
    public void testInsert() {
        // 创建项目实体对象
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置项目属性
        UUID uuid = UUID.randomUUID();
        projectEntity.setId(uuid.toString());
        projectEntity.setProjectName("测试项目名");
        projectEntity.setCreationDate(new Date());
        projectEntity.setProjectContent("测试项目内容");
        projectEntity.setCreatedBy("2");


        // 调用接口方法
        int result = projectEntityMapper.insert(projectEntity);

        // 验证结果
        Assertions.assertNotEquals(0, result);
        // 其他断言验证...
    }

    @Test
    @Transactional
    public void testDeleteProjectById() {

        ProjectEntity projectEntity = new ProjectEntity();
        // 准备要删除的项目ID
        String projectId = "bf8672e8300b494b82b6885d0fdd7dad";

        projectEntity.setId(projectId);
        // 调用接口方法
        int result = projectEntityMapper.deleteProjectById(projectEntity);

        // 验证结果
        Assertions.assertNotEquals(0, result);
        // 其他断言验证...
    }

    @Test
    @Transactional
    public void testUpdateByPrimaryKeySelective() {
        // 创建项目实体对象
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置项目属性
        projectEntity.setId("bf8672e8300b494b82b6885d0fdd7dad");
        projectEntity.setProjectContent("测试更新");
        projectEntity.setProjectName("测试更新名");
        projectEntity.setLastUpdateDate(new Date());
        projectEntity.setLastUpdatedBy("13");


        // 调用接口方法
        int result = projectEntityMapper.modifyProjectInfo(projectEntity);

        // 验证结果
        Assertions.assertNotEquals(0, result);
        // 其他断言验证...
    }
}
