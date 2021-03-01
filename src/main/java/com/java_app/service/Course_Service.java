package com.java_app.service;


import com.java_app.entity.Course;
import com.java_app.entity.Professor;



public interface Course_Service {

	public Iterable<Course> getAllCourse();
	
	public Course createCourse (Course courseForm) throws Exception;
	
	public Course getCourseById(Long id) throws Exception;
	
	public void deleteCourse(Long id) throws Exception;
	
	public Course updateCourse(Course course) throws Exception;
	
	//public Iterable<Professor> courseandprof();
}
