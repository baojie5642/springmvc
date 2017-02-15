package com.baojie.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baojie.springmvc.model.User;

public interface UserDao extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User>{
    
}
