package com.java_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_app.entity.Professor;
import com.java_app.repository.Professor_Repository;

@Service
public class Professor_ServiceImp implements Professor_Service{

	@Autowired
	Professor_Repository professor_repository;
	
	@Override
	public Iterable<Professor> getAllProfessors() {
		return professor_repository.findAll();
	}

	@Override
	public Professor getProfessorById(Long id) throws Exception {
		return professor_repository.findById(id).orElseThrow(() -> new Exception("El Profesor no Existe"));
	}

	@Override
	public void deleteProfessor(Long id) throws Exception {
		Professor professor = professor_repository.findById(id).orElseThrow(() -> new Exception("No se puede eliminar el Profesor -"+this.getClass().getName()));
		professor_repository.delete(professor);
		
	}

	@Override
	public Professor updateProfessor(Professor fromprofessor) throws Exception {
		Professor toprofessor = getProfessorById(fromprofessor.getId());
		mapUser(fromprofessor, toprofessor);
		return professor_repository.save(toprofessor);
	}
	
	protected void mapUser(Professor from,Professor to) {
		to.setName(from.getName());
		to.setLast_name(from.getLast_name());
		to.setDni(from.getDni());
		to.setActive(from.isActive());
		}

	@Override
	public Professor createProf(Professor prof) throws Exception {
		prof = professor_repository.save(prof);
		return prof;
	}

}
