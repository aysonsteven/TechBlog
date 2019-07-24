package com.blog.tech.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.tech.dao.CategoriesDao;
import com.blog.tech.dao.UserDao;
import com.blog.tech.model.TblCategories;
import com.blog.tech.model.TblUser;
import com.blog.tech.service.CategoriesService;

@Primary
@Service(value="categoriesService")
public class CategoriesServiceImpl implements UserDetailsService, CategoriesService {

	@Autowired
	private CategoriesDao categoriesDao;
	@Autowired
	private UserDao userDao;
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TblUser user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new User(user.getUsername(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	@Override
	public List<TblCategories> findAllCategories() {
		List<TblCategories> categoryList = new ArrayList<TblCategories>();
		categoriesDao.findAll().iterator().forEachRemaining(categoryList::add);
		return categoryList;
	}




	
}
