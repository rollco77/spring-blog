package com.millky.blog.presentation.controller.rest;

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


         Scraping scraping = new Scraping();
         scraping.setKeyword(productName);
         webCrawlerService.productSearchCrawl(scraping);
         return "Ok";
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
        scraping.setChannel("NV");
        scraping.setCreatedAt(new Date());

        webCrawlerService.scraping(scraping);

        // webCrawlerService.productSearchCrawl(productName);
        return keyword;
    }
}
