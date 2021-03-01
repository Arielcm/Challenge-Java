package com.java_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java_app.entity.User;

@Repository
public interface User_Repository extends CrudRepository<User, Long>{
	
	public User findBydni(String dni);

}
