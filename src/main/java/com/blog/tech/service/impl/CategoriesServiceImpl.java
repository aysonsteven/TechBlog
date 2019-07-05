package com.blog.tech.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.tech.dao.CategoriesDao;
import com.blog.tech.model.TblCategories;

import com.blog.tech.service.CategoriesService;


@Service(value="categoriesService")
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoriesDao categoriesDao;
	
	@Override
	public List<TblCategories> findAllCategories() {
		List<TblCategories> categoryList = new ArrayList<TblCategories>();
		categoryList = categoriesDao.findAll();
		return categoryList;
	}

	
}
