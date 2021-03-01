package com.java_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.java_app.entity.Professor;
import com.java_app.service.Professor_Service;

@Controller
public class Professor_Controller {

	@Autowired
	Professor_Service professor_service;
	
	
	//Listado de Profesores
	@GetMapping("/Profesores")
	public String list_prof(Model model){
		model.addAttribute("profForm", new Professor());
		model.addAttribute("listaprof", professor_service.getAllProfessors());
		model.addAttribute("listTab","active");
		return "Professor/professor-view";
		}
	
	//Agregar Profesor
	@PostMapping("/Profesores")
	public String create_prof(@ModelAttribute("profForm")Professor prof, ModelMap model) throws Exception {
		professor_service.createProf(prof);
		return "redirect:/Profesores";
	}
	
	//Eliminar Profesor
	@GetMapping("/deleteprof/{id}")
	public String deleteProf(Model model,@PathVariable(name="id")Long id) {
		try {
			professor_service.deleteProfessor(id);
		} catch (Exception e) {
			model.addAttribute("deleteError","The Professor could not be deleted.");
		}
		return "redirect:/Profesores";
		
	}
	
	//Editar Curso - Rellena el Form
	@GetMapping("/editProf/{id}")
	public String getEditProf(Model model,@PathVariable(name="id")Long id) throws Exception {
		Professor prof = professor_service.getProfessorById(id);
		model.addAttribute("listaprof", professor_service.getAllProfessors());
		model.addAttribute("profForm", prof);
		model.addAttribute("formTab","active");//Activa el tab del formulario.
		
		model.addAttribute("editMode",true);//Mira siguiente seccion para mas informacion
		
		return "Professor/professor-view";
	}
	
	//Recibe POST de Form Editar Curso
	@PostMapping("/editProf")
	public String postEditCourse(@ModelAttribute("profForm")Professor prof, ModelMap model) throws Exception {
		professor_service.updateProfessor(prof);
		return "redirect:/Profesores";
	}
	
	//Boton Cancelar del Formulario
	@GetMapping("prof/cancel")
	public String cancelEditCurse(ModelMap model) {
		return "redirect:/Profesores";
	}
}
