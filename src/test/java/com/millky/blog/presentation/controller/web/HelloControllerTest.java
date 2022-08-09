package com.millky.blog.presentation.controller.web;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName  : com.millky.blog.presentation.controller.web
 * fileName     : HelloControllerTest
 * author       : suhwan
 * date         : 2022/08/04
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/04         suhwan        최초생성
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc


public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello()throws Exception{
        String hello="hello";
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}