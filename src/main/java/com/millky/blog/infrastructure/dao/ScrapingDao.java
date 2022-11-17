package com.millky.blog.infrastructure.dao;

import com.millky.blog.domain.model.entity.Scraping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScrapingDao extends JpaRepository<Scraping, Integer> {

	public Scraping findById(UUID id);

}
