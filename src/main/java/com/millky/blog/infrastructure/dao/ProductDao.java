package com.millky.blog.infrastructure.dao;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

	public Product findById(int id);

	public List<Product> findAllByRegDateBetween(Date startDate , Date endDate);

}
