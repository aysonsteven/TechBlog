package com.blog.tech.controller;

import com.blog.tech.config.JwtTokenUtil;
import com.blog.tech.dto.LoginUser;
import com.blog.tech.dto.ApiResponse;
import com.blog.tech.dto.AuthToken;
import com.blog.tech.model.TblCategories;
import com.blog.tech.model.TblUser;
import com.blog.tech.service.UserService;
import com.blog.tech.service.impl.CategoriesServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired 
    CategoriesServiceImpl categoriesService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	List<TblCategories> categoryList = new ArrayList<TblCategories>();
    	
    	categoryList = categoriesService.findAllCategories();
    	
    	System.out.println( categoryList);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final TblUser user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<>(HttpStatus.OK.value(), "success",new AuthToken(token, user.getUsername()));
    }

}
