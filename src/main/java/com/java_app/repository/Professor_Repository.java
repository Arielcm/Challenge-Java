package com.java_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java_app.entity.Course;
import com.java_app.entity.Professor;

@Repository
public interface Professor_Repository extends CrudRepository<Professor, Long>{

	/*
	@Query(value = "SELECT name FROM courses join course_professor WHERE courses.id = course_professor.professor_id", nativeQuery = true)
	Professor courseandprofessor(Long id);
	*/
}
