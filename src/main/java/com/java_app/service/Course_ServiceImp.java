package com.java_app.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.java_app.entity.Course;
import com.java_app.entity.Professor;
import com.java_app.entity.Student;
import com.java_app.repository.Course_Repository;
import com.java_app.repository.Student_Repository;

@Service
public class Course_ServiceImp implements Course_Service{

	@Autowired
	 Course_Repository course_repository;
	
	@Autowired
	 Student_Repository student_repository;
	
	@Override
	public Iterable<Course> getAllCourse() {
		return course_repository.findAll();
	}

	@Override
	public Course getCourseById(Long id) throws Exception {

		return course_repository.findById(id).orElseThrow(() -> new Exception("El Curso no Existe"));
	}

	@Override
	public void deleteCourse(Long id) throws Exception {
		Course course = course_repository.findById(id).orElseThrow(() -> new Exception("No se puede eliminar el Curso -"+this.getClass().getName()));
		course_repository.delete(course);
		
	}

	@Override
	public Course updateCourse(Course fromcourse) throws Exception {
		Course tocourse = getCourseById(fromcourse.getId());
		mapUser(fromcourse, tocourse);
		return course_repository.save(tocourse);
	}
	
	protected void mapUser(Course from,Course to) {
		to.setName(from.getName());
		to.setTotal_students(from.getTotal_students());
		to.setClass_schedule(from.getClass_schedule());
	}


	@Override
	public Course createCourse(Course course) throws Exception {
		course =course_repository.save(course) ;
		return course;
	}

	/*
	@Override
	public Iterable<Professor> courseandprof() {
		return course_repository.courseandprofessor();
	}
	*/

}
