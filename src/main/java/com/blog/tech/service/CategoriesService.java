package com.blog.tech.service;

import java.util.List;

import com.blog.tech.model.TblCategories;

public interface CategoriesService {
	List<TblCategories> findAllCategories();
}
