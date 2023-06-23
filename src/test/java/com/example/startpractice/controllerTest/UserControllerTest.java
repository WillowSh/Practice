package com.example.startpractice.controllerTest;

import com.example.startpractice.controller.UserController;
import com.example.startpractice.dao.entity.UserEntity;
import com.example.startpractice.service.UserService;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
/*
    @Test
    public void testUserLogin_ValidUser_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin_J");
        userEntity.setPassword("123");

        List<UserEntity> users = new ArrayList<>();
        users.add(userEntity);

        when(userService.selectUserInfo(eq(userEntity))).thenReturn(users);

        mockMvc.perform(post("/admin/userLogin")
                        .content("{\"username\":\"testName\",\"password\":\"testPassword\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("登录成功"))
                .andExpect(jsonPath("$.data[0].username").value("testName"));
    }

 */
    @Test
    public void testUserLogin_ValidUser_Error() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testName");
        userEntity.setPassword("testPassword");

        List<UserEntity> users = new ArrayList<>();

        when(userService.selectUserInfo(eq(userEntity))).thenReturn(users);

        mockMvc.perform(post("/admin/userLogin")
                        .content("{\"username\":\"testName\",\"password\":\"testPassword\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.message").value("用户名或密码错误"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

/*
    @Test
    public void testAddUserInfo_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPassword");

        when(userService.addUserInfo(eq(userEntity))).thenReturn(1);

        mockMvc.perform(post("/admin/addUserInfo")
                        .content("{\"username\":\"testUser\",\"password\":\"testPassword\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("创建成功"))
                .andExpect(jsonPath("$.data").value(1));
    }
*/
    @Test
    public void testAddUserInfo_Failure() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPassword");

        when(userService.addUserInfo(eq(userEntity))).thenReturn(0);

        mockMvc.perform(post("/admin/addUserInfo")
                        .content("{\"username\":\"testUser\",\"password\":\"testPassword\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.message").value("创建失败"))
                .andExpect(jsonPath("$.data").value(0));
    }
    /*
    @Test
    public void testModifyUserInfo_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setUsername("testUser");
        userEntity.setPassword("newPassword");

        when(userService.modifyUserInfo(eq(userEntity))).thenReturn(1);

        mockMvc.perform(put("/admin")
                        .content("{\"id\":1,\"username\":\"testUser\",\"password\":\"newPassword\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("修改成功"))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    public void testModifyUserInfo_Failure() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setUsername("testUser");
        userEntity.setPassword("newPassword");

        when(userService.modifyUserInfo(eq(userEntity))).thenReturn(0);

        mockMvc.perform(put("/admin")
                        .content("{\"id\":1,\"username\":\"testUser\",\"password\":\"newPassword\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("000"))
                .andExpect(jsonPath("$.message").value("修改失败"))
                .andExpect(jsonPath("$.data").value(0));
    }
    @Test
    public void testDeleteUserById_Success() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");

        when(userService.deleteUserById(eq(userEntity))).thenReturn(1);

        mockMvc.perform(delete("/admin")
                        .content("{\"id\":1}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("666"))
                .andExpect(jsonPath("$.message").value("删除成功"))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    public void testDeleteUserById_Failure() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");

        when(userService.deleteUserById(eq(userEntity))).thenReturn(0);

        mockMvc.perform(delete("/admin")
                        .content("{\"id\":1}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("000"))
                .andExpect(jsonPath("$.message").value("删除失败"))
                .andExpect(jsonPath("$.data").value(0));
    }


*/

}
