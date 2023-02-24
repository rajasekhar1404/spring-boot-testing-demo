package com.testing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.entity.User;
import com.testing.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    private User user;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUpTest() {
        user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.setAddress("address");
        user.setId(1);
    }

    @Test
    @WithMockUser
    void findAllUsersTest() throws Exception {
        when(userService.findAllUsers()).thenReturn(Arrays.asList(user));
        mockMvc.perform(get("/api/v1/users")
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void findUserByIdTest() throws Exception {
        when(userService.findUserById(user.getId())).thenReturn(user);
        mockMvc.perform(get("/api/v1/user/"+user.getId())).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void addUserTest() throws Exception {
        mockMvc.perform(post("/api/v1/user")
                .content(mapToJson(user))
        ).andExpect(status().isCreated());
    }

    @Test
    void updateUserTest() {
    }

    @Test
    void deleteUserByIdTest() {
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}