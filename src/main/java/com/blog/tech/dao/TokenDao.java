package com.blog.tech.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blog.tech.model.TblTokens;

public interface TokenDao extends CrudRepository<TblTokens, Integer>{

//	TokenDto findByTokenName(@Param("tokenName") String tokenName);
	TblTokens findByToken(String tokenName);
}
