package com.millky.blog.domain.service;

import com.millky.blog.domain.model.entity.Product;
import com.millky.blog.domain.model.entity.Scraping;

/**
 * packageName  : com.millky.blog.domain.service.impl
 * fileName     : WebCrawlerService
 * author       : suhwan
 * date         : 2022/08/04
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/04         suhwan        최초생성
 */
public interface WebCrawlerService {
    public void productSearchCrawl(Scraping scraping);
    public void productReviewCrawl(Product product);
    public void selectProductTest();
    public void scraping(Scraping scraping);
}
