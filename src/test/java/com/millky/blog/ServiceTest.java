package com.millky.blog;



import com.millky.blog.domain.model.entity.Post;

import com.millky.blog.domain.service.PostSearchService;
import com.millky.blog.domain.service.WebCrawlerService;
import com.millky.blog.domain.service.impl.NaverProductWebCrawlerService;
import com.rollco7.service.JunitTest2Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class  ServiceTest{

    @Autowired
    private WebCrawlerService naverProductWebCrawlerService;

    //@Autowired
    //private PostSearchService postSearchRepository;


    @Test
    public void productCrawlTest() {
     //   String content = junitTest2Service.getPostContent();
        naverProductWebCrawlerService.productSearchCrawl("건어물");
        //log.info("content:{}",content);
       // naverProductWebCrawlerService.productReviewCrawl();
    }


    @Test
    public void selectProductTest() {
        naverProductWebCrawlerService.selectProductTest();
    }

    @Test
    public void productReviewCrawl(){
        naverProductWebCrawlerService.productReviewCrawl();
    }
}
