package com.blog.tech.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.tech.dto.ApiResponse;
import com.blog.tech.dto.CategoriesDto;
import com.blog.tech.model.TblTokens;
import com.blog.tech.service.TokenService;
import com.blog.tech.service.impl.CategoriesServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoriesServiceImpl categoriesService;
	
	@Autowired
    private TokenService tokenService;
	
	@GetMapping("/getall")
	public ApiResponse<CategoriesDto> getAllCategories(@RequestHeader(value="Authorization") String token) {
//		TblUser loggedUser = userService.findOne("user");
		TblTokens tokenObject = tokenService.findTokenByName(token.replaceFirst("Bearer ", ""));
    	if(tokenObject == null || tokenObject.getToken() =="") {
    		return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "Not Allowed", new ArrayList<>());
    	}
		return new ApiResponse<CategoriesDto>(HttpStatus.OK.value(), "success", categoriesService.findAllCategories());
	}
	
	@GetMapping("/getcategory/{id}")
	public ApiResponse<CategoriesDto> getCategory(@RequestHeader(value="Authorization") String token, @PathVariable(value="id") Integer id){
		TblTokens tokenObject = tokenService.findTokenByName(token.replaceFirst("Bearer ", ""));
    	if(tokenObject == null || tokenObject.getToken() =="") {
    		return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "Not Allowed", new ArrayList<>());
    	}
		return new ApiResponse<CategoriesDto>(HttpStatus.OK.value(), "success", categoriesService.findCategoryById(id));
	}

}
