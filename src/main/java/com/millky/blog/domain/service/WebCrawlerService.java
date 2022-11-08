package com.millky.blog.domain.service;

import com.millky.blog.domain.model.entity.Product;
import com.millky.blog.domain.model.entity.ProductReview;
import com.millky.blog.domain.model.entity.Scraping;

import java.util.List;
import java.util.UUID;

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
    public Scraping findScraping(UUID scrapingId);
    public List<Scraping> findScrapingAll ();
    public Scraping insertScraping(Scraping scraping);
    public Scraping saveScraping(Scraping scraping);

    public Product findByProductId(int productId);
    public List<Product> findAllByScrapingId(UUID scrapingId);
    public List<ProductReview> findProductReviewAllByProductId(int productId);

    public void sentimentAnalysisProduct(int productId);

    public void textSummaryAnalysisProduct(int productId);

}
