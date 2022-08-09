package com.millky.blog.domain.service.impl;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.repository.PostRepository;
import com.millky.blog.domain.service.JunitTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * packageName  : com.rollco7.service.impl
 * fileName     : JunitTestServiceImpl
 * author       : suhwan
 * date         : 2022/08/04
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/04         suhwan        최초생성
 */
@Service("junitTestService")
public class JunitTestServiceImpl implements JunitTestService {
    @Autowired
    PostRepository postRepository;

    @Override
    @Transactional
    public String getPostContent() {
        Post post = postRepository.getPostById(3);
        String content = post.getContent();
        return content;
    }
}
