package com.blog.tech.service;

import com.blog.tech.dto.TokenDto;
import com.blog.tech.model.TblTokens;

public interface TokenService {
	String inserTokens(TokenDto token, int uid);

	TblTokens findTokenByName(String token);
	
	String deleteTokenByTokenName(String token);
	
	
}
