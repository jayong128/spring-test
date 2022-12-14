package com.example.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data Rest 테스트는 불필요")
@DisplayName("Data Rest - API 테스트")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class DataRestTest {
    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("api 게시글 리스트 테스트")
    void articles_test() throws Exception {
        //given

        //when
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //then
    }

    @Test
    @DisplayName("api 게시글 단건 테스트")
    void article_test() throws Exception {
        //given

        //when
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //then
    }

    @Test
    @DisplayName("api 게시글의 댓글 리스트 테스트")
    void articleComments() throws Exception {
        //given

        //when
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //then
    }

    @Test
    @DisplayName("api 댓글 테스트")
    void articleComments_test() throws Exception {
        //given

        //when
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //then
    }

    @Test
    @DisplayName("api 댓글 단건 테스트")
    void articleComment() throws Exception {
        //given

        //when
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        //then
    }
}
