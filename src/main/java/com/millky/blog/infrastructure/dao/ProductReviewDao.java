package com.millky.blog.infrastructure.dao;

import com.millky.blog.domain.model.entity.ScrapingProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewDao extends JpaRepository<ScrapingProductReview, Integer> {

	public ScrapingProductReview findById(int id);

	public List<ScrapingProductReview> findAllByProductId(int productId);

	//public List<ProductReview> findAllByProductId(Date startDate , Date endDate);

}
