package com.java_app.service;

import com.java_app.entity.Professor;

public interface Professor_Service {

	public Iterable<Professor> getAllProfessors();
	
	public Professor createProf(Professor profForm) throws Exception;
	
	public Professor getProfessorById(Long id) throws Exception;
	
	public void deleteProfessor(Long id) throws Exception;
	
	public Professor updateProfessor(Professor professor) throws Exception;
}
