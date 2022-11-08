package com.millky.blog.domain.repository;

import com.millky.blog.domain.model.entity.Product;
import com.millky.blog.domain.model.entity.ProductReview;
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

    public Product findPrductById(int id){
        return productDao.findById(id);
    }

    public List<Product> findProductAllByScrapingId(UUID scrapingId){
        return productDao.findAllByScrapingId(scrapingId);
    }

    public Product createProduct( Product product) {
        Product resultProduct = productDao.save(product);
        return resultProduct;
    }

    public Product saveProduct( Product product) {
        product.setModDate(new Date());
        Product resultProduct = productDao.save(product);
        return resultProduct;
    }


    public ProductReview findPrductReviewById(int id){
        return productReviewDao.findById(id);
    }

    public List<ProductReview> findProductReviewAllByProductId(int productId){
        return productReviewDao.findAllByProductId(productId);
    }

    public ProductReview createProductReview( ProductReview productReview) {
        ProductReview resultProductReview = productReviewDao.save(productReview);
        return resultProductReview;
    }

    public ProductReview saveProductReview( ProductReview productReview) {
        productReview.setModDate(new Date());
        ProductReview resultProductReview = productReviewDao.save(productReview);
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
