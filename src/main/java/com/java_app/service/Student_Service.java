package com.java_app.service;

import com.java_app.entity.Student;

public interface Student_Service {
	
	public Student findBydni(String dni)throws Exception; 
	
	public Student getStudentById(Long id)throws Exception; 
}
