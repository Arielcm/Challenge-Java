package com.java_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_app.entity.Student;
import com.java_app.repository.Student_Repository;

@Service
public class Student_ServiceImp implements Student_Service{

	@Autowired
	Student_Repository student_repository;
	
	@Override
	public Student findBydni(String dni) throws Exception {
		
		return student_repository.findBydni(dni);
	}

	@Override
	public Student getStudentById(Long id) throws Exception {
		return student_repository.findById(id).orElseThrow(() -> new Exception("El Estudiante no Existe"));
	}

}
