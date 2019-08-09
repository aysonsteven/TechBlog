package com.blog.tech.controller;

import jar.blog.tech.dto.ApiResponse;
import jar.blog.tech.dto.AuthToken;
import jar.blog.tech.dto.LoginUser;
import jar.blog.tech.dto.TokenDto;
import jar.blog.tech.model.TblTokens;
import jar.blog.tech.model.TblUser;
import jar.blog.tech.service.TokenService;
import jar.blog.tech.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import guard.blog.tech.config.JwtTokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	@Autowired(required = true)
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/generate-token")
	public ApiResponse<AuthToken> login(@RequestBody LoginUser loginUser) throws AuthenticationException {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		final TblUser user = userService.findOne(loginUser.getUsername());
		final String token = jwtTokenUtil.generateToken(user.getUsername());
		TokenDto tokenObject = new TokenDto();
		tokenObject.setToken(token);
		return new ApiResponse<>(HttpStatus.OK.value(), tokenService.inserTokens(tokenObject, user.getId()),
				new AuthToken(token, loginUser.getUsername()));
	}
	
	@DeleteMapping("/logout")
	public ApiResponse<String> logout(@RequestHeader(value="Authorization") String token){
		String tokenObject = tokenService.deleteTokenByTokenName(token.replaceAll("Bearer ", ""));
		return new ApiResponse<>(HttpStatus.OK.value(), "deleted", tokenObject);
	}
	
	@GetMapping("/checklogin")
	public ApiResponse<Boolean> checklogin( @RequestHeader(value="Authorization") String token){
		TblTokens tokenObject = tokenService.findTokenByName( token.replaceAll("Bearer ", ""));
		Boolean isTokenValid = false;
		if( tokenObject != null ) {
			isTokenValid = true;
		}
		return new ApiResponse<>(HttpStatus.OK.value(), "Login Status", isTokenValid);
	}

}
