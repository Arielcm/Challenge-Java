package com.java_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_app.entity.User;
import com.java_app.repository.User_Repository;

@Service
public class User_ServiceImp implements User_Service{
	
	@Autowired
	User_Repository user_repository;

	@Override
	public User findBydni(String dni) throws Exception {
		
		return user_repository.findBydni(dni);
	}

}
