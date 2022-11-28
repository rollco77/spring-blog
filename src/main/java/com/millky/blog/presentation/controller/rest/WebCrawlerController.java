package com.millky.blog.presentation.controller.rest;

import com.millky.blog.domain.constant.scraping.Channel;
import com.millky.blog.domain.model.entity.Comment;
import com.millky.blog.domain.model.entity.Scraping;
import com.millky.blog.domain.repository.ProductRepository;
import com.millky.blog.domain.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * packageName  : com.millky.blog.presentation.controller.rest
 * fileName     : WebCrawlerController
 * author       : suhwan
 * date         : 2022/08/23
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/23         suhwan        최초생성
 */
@RestController
public class WebCrawlerController {

    @Autowired
    WebCrawlerService webCrawlerService;

    @RequestMapping(value = "/crawler/search/{productName}", method = RequestMethod.GET)
    public String search( @PathVariable String productName) {

        String returnMsg = "OK";

        Scraping scraping = new Scraping();
        scraping.setKeyword(productName);
        try {
            webCrawlerService.productSearchCrawl(scraping);
        } catch (Exception e) {
            returnMsg = e.getMessage();
            throw new RuntimeException(e);
        }
        return returnMsg;
    }

    @RequestMapping(value = "/crawler/test/{productName}", method = RequestMethod.GET)
    public String test( @PathVariable String productName) {

       // webCrawlerService.productSearchCrawl(productName);
        return productName;
    }

    @RequestMapping(value = "/crawler/crawlTest/{keyword}", method = RequestMethod.GET)
    public String crawlTest( @PathVariable String keyword) {

        Scraping scraping = new Scraping();

        scraping.setKeyword(keyword);
        scraping.setChannel(Channel.valueOf("NV"));
        scraping.setCreatedAt(new Date());

        webCrawlerService.scraping(scraping);

        // webCrawlerService.productSearchCrawl(productName);
        return keyword;
    }
}
