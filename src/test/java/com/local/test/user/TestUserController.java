package com.local.test.user;

/**
 * Created by meisei on 2017/3/13.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.test.user.model.User;
import com.local.test.user.service.UserService;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class TestUserController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testExample() throws Exception {
        User honda = new User("Honda", 20);
        given(this.userService.createUser(honda)).willReturn(honda);
        this.mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsBytes(honda)).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

}
