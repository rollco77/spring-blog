package com.millky.blog.infrastructure.dao;

import com.millky.blog.domain.model.entity.Product;
import com.millky.blog.domain.model.entity.Scraping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ScrapingDao extends JpaRepository<Scraping, Integer> {

	public Scraping findById(int id);


}
