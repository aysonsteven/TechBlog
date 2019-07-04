package com.blog.tech.service;

import com.blog.tech.dto.UserDto;
import com.blog.tech.model.TblUser;

import java.util.List;

public interface UserService {

    TblUser save(UserDto user);
    List<TblUser> findAll();
    void delete(int id);

    TblUser findOne(String username);

    TblUser findById(int id);

    UserDto update(UserDto userDto);
}
