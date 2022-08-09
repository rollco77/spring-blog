package com.rollco7.service.impl;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.repository.PostRepository;
import com.millky.blog.domain.service.JunitTestService;
import com.rollco7.service.JunitTest2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * packageName  : com.rollco7.service.impl
 * fileName     : JunitTest2ServiceImpl
 * author       : suhwan
 * date         : 2022/08/04
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/04         suhwan        최초생성
 */
@Service("junitTest2Service")
public class JunitTest2ServiceImpl implements JunitTest2Service {
   // @Autowired
    //PostRepository postRepository;

    @Override
    @Transactional
    public String getPostContent() {
       // Post post = postRepository.getPostById(3);
       // String content = post.getContent();
        return "!!!!!!!!!!!!!!!!!!!!!!!!";
    }
}
