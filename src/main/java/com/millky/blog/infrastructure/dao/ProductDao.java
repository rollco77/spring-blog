package com.millky.blog.infrastructure.dao;

import com.millky.blog.domain.model.entity.ScrapingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ProductDao extends JpaRepository<ScrapingProduct, Integer> {

	public ScrapingProduct findById(int id);

	public List<ScrapingProduct> findAllByRegDateBetween(Date startDate , Date endDate);

	public List<ScrapingProduct> findAllByScrapingId(UUID scrapingId);

}
