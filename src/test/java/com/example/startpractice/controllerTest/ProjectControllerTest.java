package com.example.startpractice.controllerTest;

import com.example.startpractice.controller.ProjectController;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }
/*
    @Test
    public void testQueryProjectList_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性
        projectEntity.setProjectName("propertyValue");

        List<ProjectEntity> projectList = new ArrayList<>();
        // 向 projectList 添加项目实体对象
        ProjectEntity projectEntity1=new ProjectEntity();
        projectEntity1.setProjectName("propertyValue1");
        ProjectEntity projectEntity2=new ProjectEntity();
        projectEntity2.setProjectName("propertyValue2");
        projectList.add(projectEntity1);
        projectList.add(projectEntity2);

        when(projectService.queryProjectList(eq(projectEntity))).thenReturn(projectList);

        mockMvc.perform(get("/project/queryProjectList")
                        .param("projectName", "propertyValue")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("查询成功"))
                .andExpect(jsonPath("$.data.length()").value(projectList.size()))
                .andExpect(jsonPath("$.data[0].projectName").value("propertyValue1"))
                .andExpect(jsonPath("$.data[1].projectName").value("propertyValue2"));
    }

    @Test
    public void testQueryProjectList_NoData_Failure() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性
        projectEntity.setProjectName("第一");

        when(projectService.queryProjectList(eq(projectEntity))).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/project/queryProjectList")
                        .param("projectName", "propertyValue")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.message").value("无项目信息"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void testAddProjectInfo_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性
        projectEntity.setProjectName("propertyValue");
        projectEntity.setCreatedBy("11");
        projectEntity.setUserId("11");
        projectEntity.setLastUpdatedBy("11");
        projectEntity.setCreationDate(new Date());
        projectEntity.setId("1");
        projectEntity.setProjectContent("?");

        when(projectService.addProjectInfo(eq(projectEntity))).thenReturn(1);

        mockMvc.perform(post("/addProjectInfo")
                        //.param("projectEntity",projectEntity.toString())
                        .content("{\"projectName\":\"propertyValue\",\"id\":\"1\",\"projectContent\":\"?\",\"createdBy\":\"11\",\"userId\":\"11\",\"lastUpdatedBy\":\"11\",\"creationDate\":\"2023-06-21T00:00:00Z\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("创建成功"))
                .andExpect(jsonPath("$.data").value(1));
    }
*/
    @Test
public void testAddProjectInfo_Success() throws Exception {
    ProjectEntity projectEntity = new ProjectEntity();
    projectEntity.setProjectName("propertyValue");
    projectEntity.setCreatedBy("admin_J");
    projectEntity.setUserId("1");
    projectEntity.setLastUpdatedBy("admin_J");
    projectEntity.setId("1");
    projectEntity.setProjectContent("?");

    when(projectService.addProjectInfo(eq(projectEntity))).thenReturn(1);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(projectEntity);

        String result = mockMvc.perform(post("/addProjectInfo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println("测试结果：" + result);
    mockMvc.perform(post("/addProjectInfo")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("666"))
            .andExpect(jsonPath("$.message").value("创建成功"))
            .andExpect(jsonPath("$.data").value(1));
}
    @Test
    public void testAddProjectInfo_Failure() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性

        when(projectService.addProjectInfo(eq(projectEntity))).thenReturn(0);

        mockMvc.perform(post("/addProjectInfo")
                        .content("{\"projectName\":\"propertyValue\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.message").value("创建失败"))
                .andExpect(jsonPath("$.data").value(0));
    }

    @Test
    public void testModifyProjectInfo_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性
        projectEntity.setProjectName("propertyValue");

        when(projectService.modifyProjectInfo(eq(projectEntity))).thenReturn(1);
        mockMvc.perform(put("/project/modifyProjectInfo")
                        .content("{\"projectName\":\"propertyValue\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("修改成功"))
                .andExpect(jsonPath("$.data").value(1));

    }

    @Test
    public void testModifyProjectInfo_Failure() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        // 设置 projectEntity 的属性

        when(projectService.modifyProjectInfo(eq(projectEntity))).thenReturn(0);

        mockMvc.perform(post("/modifyProjectInfo")
                        .content("{\"projectName\":\"propertyValue\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.message").value("修改失败"))
                .andExpect(jsonPath("$.data").value(0));
    }

    @Test
    public void testDeleteProjectById_Success() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        String projectId = "1";
        projectEntity.setId(projectId);

        when(projectService.deleteProjectById(eq(projectEntity))).thenReturn(1);
        System.out.println(projectService.deleteProjectById(eq(projectEntity)));
        mockMvc.perform(post("/deleteProjectById")
                        .param("id", projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("删除成功"))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    public void testDeleteProjectById_Failure() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        String projectId = "1";
        projectEntity.setId(projectId);

        when(projectService.deleteProjectById(eq(projectEntity))).thenReturn(0);

        mockMvc.perform(post("/deleteProjectById")
                        .param("id", projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.message").value("删除失败"))
                .andExpect(jsonPath("$.data").value(0));
    }
}
