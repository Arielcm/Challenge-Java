package com.java_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java_app.entity.Course;


@Repository
public interface Course_Repository extends JpaRepository<Course, Long>{

	/*
	@Query(value = "SELECT * FROM courses INNER JOIN course_professor AS m ON courses.id = m.professor_id INNER JOIN professors ON m.course_id = professors.id", nativeQuery = true)
	public Iterable<Professor> courseandprofessor();
	*/
}
