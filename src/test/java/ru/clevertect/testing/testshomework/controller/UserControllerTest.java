package ru.clevertect.testing.testshomework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertect.testing.testshomework.domain.User;
import ru.clevertect.testing.testshomework.entity.UserEntity;
import ru.clevertect.testing.testshomework.exception.NoSuchUserException;
import ru.clevertect.testing.testshomework.exception.UserExistsException;
import ru.clevertect.testing.testshomework.mappers.UserMapper;
import ru.clevertect.testing.testshomework.sevice.UserService;
import ru.clevertect.testing.testshomework.utils.TestData;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenGetAll_thenReturnAllUsers() throws Exception {
        List<UserEntity> entities = TestData.getUserEntities();

        Mockito.when(userService.getAll())
                .thenReturn(entities);

        Mockito.when(userMapper.entityToDomains(entities))
                .thenReturn(TestData.getUserDomains(entities));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(entities.size()));
    }

    @Test
    void whenGetUser_thenReturnUser() throws Exception {
        UserEntity entity = TestData.getUserEntity();
        UUID id = entity.getId();

        User user = TestData.castUserEntity(entity);

        Mockito.when(userService.getById(id))
                .thenReturn(entity);

        Mockito.when(userMapper.entityToDomain(entity))
                .thenReturn(user);

        mockMvc.perform(get("/api/v1/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }

    @Test
    void whenGetUser_thenThrowNoSuchUserException() throws Exception {
        Mockito.when(userService.getById(Mockito.any()))
                .thenThrow(NoSuchUserException.class);

        mockMvc.perform(get("/api/v1/users/{id}", UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenCreateUser_thenCreateUser() throws Exception {
        UserEntity entity = TestData.getUserEntity();
        User user = TestData.castUserEntity(entity);

        Mockito.when(userMapper.domainToEntity(user))
                .thenReturn(entity);

        Mockito.when(userService.save(entity))
                .thenReturn(entity);

        Mockito.when(userMapper.entityToDomain(entity))
                .thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(entity.getId().toString()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }

    @Test
    void whenCreateExistedUser_thenThrowUserExistsException() throws Exception {
        User user = TestData.castUserEntity(TestData.getUserEntity());

        Mockito.when(userService.save(Mockito.any()))
                .thenThrow(UserExistsException.class);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict());
    }

    @Test
    void whenUpdateUser_thenUpdateUser() throws Exception {
        UserEntity entity = TestData.getUserEntity();
        User user = TestData.castUserEntity(entity);
        UUID id = user.getId();

        Mockito.when(userMapper.domainToEntity(user))
                .thenReturn(entity);

        Mockito.when(userService.update(id, entity))
                .thenReturn(entity);

        Mockito.when(userMapper.entityToDomain(entity))
                .thenReturn(user);

        mockMvc.perform(put("/api/v1/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }

    @Test
    void whenUpdateUser_thenThrowNoSuchUserException() throws Exception {
        UserEntity entity = TestData.getUserEntity();
        User user = TestData.castUserEntity(entity);
        UUID id = user.getId();

        Mockito.when(userMapper.domainToEntity(user))
                .thenReturn(entity);

        Mockito.when(userService.update(id, entity))
                .thenThrow(NoSuchUserException.class);

        mockMvc.perform(put("/api/v1/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDelete_thenDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/{id}", UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }
}