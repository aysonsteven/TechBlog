package com.blog.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.tech.dto.ApiResponse;
import com.blog.tech.dto.CategoriesDto;
import com.blog.tech.service.impl.CategoriesServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoriesServiceImpl categoriesService;
//	@Autowired
//	private UserService userService;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/getall")
	public ApiResponse<CategoriesDto> getAllCategories() {
//		TblUser loggedUser = userService.findOne("user");
		return new ApiResponse<CategoriesDto>(HttpStatus.OK.value(), "success", categoriesService.findAllCategories());
	}

}
