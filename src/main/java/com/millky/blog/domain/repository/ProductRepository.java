package com.millky.blog.domain.repository;

import com.millky.blog.domain.model.entity.ScrapingProduct;
import com.millky.blog.infrastructure.dao.ProductDao;
import com.rollco7.util.RollcoDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * packageName  : com.millky.blog.domain.repository
 * fileName     : ProductRepository
 * author       : suhwan
 * date         : 2022/08/18
 * description  :
 * ================================================
 * Date             AUTHOR          NOTE
 * 2022/08/18         suhwan        최초생성
 */
@Repository
public class ProductRepository {
    @Autowired
    ProductDao productDao;

    public ScrapingProduct findPrductById(int id){
        return productDao.findById(id);
    }

    public List<ScrapingProduct> findAllByScrapingId(UUID scrpaingId){
        return productDao.findAllByScrapingId(scrpaingId);
    }

    public List<ScrapingProduct> findAllProductByDate(String yyyyMMddDate){
        // Date

        if(yyyyMMddDate == null || yyyyMMddDate.length() < 8){
            return null;
        }
        Date startDateCondition   = RollcoDateUtil.getPreviousDateZeroTime(yyyyMMddDate);
        Date endDateCondition     = RollcoDateUtil.getDateZeroTime(yyyyMMddDate);
        return productDao.findAllByRegDateBetween(startDateCondition,endDateCondition);
    }

    public void createProduct( ScrapingProduct product) {
        ScrapingProduct resultProduct = productDao.save(product);
    }

}
