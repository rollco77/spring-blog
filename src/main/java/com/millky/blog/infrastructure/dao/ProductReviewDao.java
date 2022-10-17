package com.millky.blog.infrastructure.dao;

import com.millky.blog.domain.model.entity.Product;
import com.millky.blog.domain.model.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductReviewDao extends JpaRepository<ProductReview, Integer> {

	public ProductReview findById(int id);

	public List<ProductReview> findAllByProductId(int productId);

	//public List<ProductReview> findAllByProductId(Date startDate , Date endDate);

}
