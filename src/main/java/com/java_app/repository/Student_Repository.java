package com.java_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java_app.entity.Student;

@Repository
public interface Student_Repository extends JpaRepository<Student, Long>{

	public Student findBydni(String dni); 
}
