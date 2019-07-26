package com.blog.tech.controller;

import com.blog.tech.config.JwtTokenUtil;
import com.blog.tech.dto.LoginUser;
import com.blog.tech.dto.TokenDto;
import com.blog.tech.dto.ApiResponse;
import com.blog.tech.dto.AuthToken;
import com.blog.tech.model.TblTokens;
import com.blog.tech.model.TblUser;
import com.blog.tech.service.TokenService;
import com.blog.tech.service.UserService;

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
		final String token = jwtTokenUtil.generateToken(user);
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
