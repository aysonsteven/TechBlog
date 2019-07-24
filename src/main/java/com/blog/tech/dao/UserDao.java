package com.blog.tech.dao;

import com.blog.tech.model.TblUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<TblUser, Integer> {

    TblUser findByUsername(String username);
    
    TblUser findById( int id );
}
