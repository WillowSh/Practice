package com.example.startpractice.serviceTest;

import com.example.startpractice.dao.ProjectEntityMapper;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectEntityMapper projectEntityMapper;

    @Test
    public void testAddProjectInfo() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性
        projectEntity.setProjectName("projectName");
        projectEntity.setProjectContent("projectContent");
        projectEntity.setCreatedBy("13");
        projectEntity.setUserId("13");
        when(projectEntityMapper.insert(eq(projectEntity))).thenReturn(1);

        int result = projectService.addProjectInfo(projectEntity);
        assertEquals(1, result);

    }

    @Test
    public void testModifyProjectInfo() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性
        when(projectEntityMapper.modifyProjectInfo(eq(projectEntity))).thenReturn(1);

        int result = projectService.modifyProjectInfo(projectEntity);
        assertEquals(1, result);
    }

    @Test
    public void testDeleteProjectById() {
        ProjectEntity projectEntity = new ProjectEntity();
        String projectId = "bf8672e8300b494b82b6885d0fdd7dad";
        projectEntity.setId(projectId);
        when(projectEntityMapper.deleteProjectById(eq(projectEntity))).thenReturn(1);

        int result = projectService.deleteProjectById(projectEntity);
        assertEquals(1, result);
    }

    @Test
    public void testQueryProjectList1() throws Exception {
        ProjectEntity condition = new ProjectEntity();
        // 设置查询条件的属性

        List<ProjectEntity> projectList = new ArrayList<>();
        // 向 projectList 添加项目实体对象

        when(projectEntityMapper.queryProjectList(eq(condition))).thenReturn(projectList);

        List<ProjectEntity> result = projectService.queryProjectList(condition);
        assertEquals(projectList, result);
    }
    @Test
    public void testQueryProjectList2() throws Exception {
        ProjectEntity condition = new ProjectEntity();
        // 设置查询条件的属性
        condition.setProjectName("First");

        List<ProjectEntity> projectList = new ArrayList<>();
        // 向 projectList 添加项目实体对象

        when(projectEntityMapper.queryProjectList(eq(condition))).thenReturn(projectList);

        List<ProjectEntity> result = projectService.queryProjectList(condition);
        assertEquals(projectList, result);
    }

}
