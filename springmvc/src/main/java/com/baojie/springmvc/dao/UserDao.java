package com.baojie.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baojie.springmvc.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    
}
