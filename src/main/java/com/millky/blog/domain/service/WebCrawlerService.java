package com.millky.blog.domain.service;

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
    public void productSearchCrawl(String productName);
    public void productReviewCrawl();
}
