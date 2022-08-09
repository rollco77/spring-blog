package com.millky.blog.repository.test;

/**
 * packageName  : com.millky.blog.repository.test
 * fileName     : com.rollco7.PostRepositoryTest
 * author       : suhwan
 * date         : 2022/08/04
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/04         suhwan        최초생성
 */


import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.repository.PostRepository;

import com.millky.blog.domain.service.PostSearchService;
import com.millky.blog.domain.service.JunitTestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JunitTestService junitTestService;

    @Autowired
    private PostSearchService contents;

    @Test
    public void select() {
        // given
        Post post = postRepository.getPostById(3);
        String content = junitTestService.getPostContent();
        log.info("content:{}",content);
       // post.getContent();
//        System.out.println(post.getContent());
        //assertEquals("aaa", post.getContent());
        //as
    }
}
