package com.vac.main.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        // var ctx = new WebApplicationContext();

        // this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

    }

    @Test
    void test() {
        fail("Not yet implemented");
    }

}
