package com.blog.tech.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.tech.model.TblCategories;

@Repository
public interface CategoriesDao extends CrudRepository<TblCategories, Integer> {
	@Query(nativeQuery = true, value="SELECT * FROM categories WHERE id= :id")
	TblCategories fineOne( Integer id );
}
