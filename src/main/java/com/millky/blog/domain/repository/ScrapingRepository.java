package com.millky.blog.domain.repository;

import com.millky.blog.domain.model.entity.ScrapingProduct;
import com.millky.blog.domain.model.entity.ScrapingProductReview;
import com.millky.blog.domain.model.entity.Scraping;
import com.millky.blog.infrastructure.dao.ProductDao;
import com.millky.blog.infrastructure.dao.ProductReviewDao;
import com.millky.blog.infrastructure.dao.ScrapingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * packageName  : com.millky.blog.domain.repository
 * fileName     : ScrapingRepository
 * author       : suhwan
 * date         : 2022/10/12
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/10/12         suhwan        최초생성
 */
@Repository
public class ScrapingRepository {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductReviewDao productReviewDao;

    @Autowired
    private ScrapingDao scrapingDao;

    public ScrapingProduct findPrductById(int id){
        return productDao.findById(id);
    }

    public List<ScrapingProduct> findProductAllByScrapingId(UUID scrapingId){
        return productDao.findAllByScrapingId(scrapingId);
    }

    public ScrapingProduct createProduct(ScrapingProduct product) {
        ScrapingProduct resultProduct = productDao.save(product);
        return resultProduct;
    }

    public ScrapingProduct saveProduct(ScrapingProduct product) {
        product.setModDate(new Date());
        ScrapingProduct resultProduct = productDao.save(product);
        return resultProduct;
    }


    public ScrapingProductReview findPrductReviewById(int id){
        return productReviewDao.findById(id);
    }

    public List<ScrapingProductReview> findProductReviewAllByProductId(int productId){
        return productReviewDao.findAllByProductId(productId);
    }

    public ScrapingProductReview createProductReview(ScrapingProductReview productReview) {
        ScrapingProductReview resultProductReview = productReviewDao.save(productReview);
        return resultProductReview;
    }

    public ScrapingProductReview saveProductReview(ScrapingProductReview productReview) {
        productReview.setModDate(new Date());
        ScrapingProductReview resultProductReview = productReviewDao.save(productReview);
        return resultProductReview;
    }

    public Scraping findScrapingById(UUID id){
        return scrapingDao.findById(id);
    }

    public Scraping createScraping( Scraping scraping) {
        Scraping resultScraping = scrapingDao.save(scraping);
        return resultScraping;
    }
    public Scraping saveScraping( Scraping scraping) {
        scraping.setModifiedAt(new Date());
        Scraping resultScraping = scrapingDao.save(scraping);
        return resultScraping;
    }

    public List<Scraping> findScrapingAll(){
        return scrapingDao.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }



}
