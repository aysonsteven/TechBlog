package com.blog.tech.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.tech.model.TblCategories;

@Repository
public interface CategoriesDao extends CrudRepository<TblCategories, Integer> {
	
	List<TblCategories> findAll();
}
