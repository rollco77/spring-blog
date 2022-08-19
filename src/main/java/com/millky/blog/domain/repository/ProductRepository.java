package com.millky.blog.domain.repository;

import com.millky.blog.domain.model.entity.PostTag;
import com.millky.blog.domain.model.entity.Product;
import com.millky.blog.infrastructure.dao.ProductDao;
import com.rollco7.util.RollcoDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * packageName  : com.millky.blog.domain.repository
 * fileName     : ProductRepository
 * author       : suhwan
 * date         : 2022/08/18
 * description  :
 * ==========================================================
 * Date             AUTHOR          NOTE
 * 2022/08/18         suhwan        최초생성
 */
@Repository
public class ProductRepository {
    @Autowired
    ProductDao productDao;

    public Product findPrductById(int id){
        return productDao.findById(id);
    }

    public List<Product> findAllProductByDate(String yyyyMMddDate){
        // Date

        if(yyyyMMddDate == null || yyyyMMddDate.length() < 8){
            return null;
        }
        Date startDateCondition   = RollcoDateUtil.getPreviousDateZeroTime(yyyyMMddDate);
        Date endDateCondition     = RollcoDateUtil.getDateZeroTime(yyyyMMddDate);
        return productDao.findAllByRegDateBetween(startDateCondition,endDateCondition);
    }

    public void createProduct( Product product) {
        Product resultProduct = productDao.save(product);
    }

}
