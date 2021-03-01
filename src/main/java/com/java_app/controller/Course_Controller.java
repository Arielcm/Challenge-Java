package com.java_app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.java_app.entity.Course;
import com.java_app.entity.Professor;
import com.java_app.repository.Course_Repository;
import com.java_app.service.Course_Service;
import com.java_app.service.Professor_Service;

@Controller
public class Course_Controller {

	@Autowired
	Course_Service course_service;
	
	@Autowired
	Professor_Service professor_service;
	
	@Autowired
	Course_Repository crep;
	
	//Listado de Cursos
	@GetMapping("/Cursos")public String list_course(Model model) {
		
		model.addAttribute("courseForm", new Course());
		model.addAttribute("listacourse", course_service.getAllCourse());
		model.addAttribute("listTab","active");
		return "Course/course-view";
	}
	
	//Agregar Curso
	@PostMapping("/Cursos")
	public String create_course(@ModelAttribute("courseForm")Course course, ModelMap model) {
			try {
				course_service.createCourse(course);
			} catch (Exception e) {
				model.addAttribute("formError",e.getMessage());
				model.addAttribute("courseForm", course);
				model.addAttribute("formTab","active");
			}
		return "redirect:/Cursos";
	}
	
	
	//Boton Cancelar del Formulario
	@GetMapping("course/cancel")
	public String cancelEditCurse(ModelMap model) {
		return "redirect:/Cursos";
	}
	
	
	//Eliminar Curso
	@GetMapping("/deletecourse/{id}")
	public String deleteCourse(Model model,@PathVariable(name="id")Long id) {
		try {
			course_service.deleteCourse(id);
		} catch (Exception e) {
			model.addAttribute("deleteError","The Course could not be deleted.");
		}
		return "redirect:/Cursos";
		
	}
	
	//Editar Curso - Rellena el Form
	@GetMapping("/editCourse/{id}")
	public String getEditCourse(Model model,@PathVariable(name="id")Long id) throws Exception {
		Course course = course_service.getCourseById(id);		
		model.addAttribute("listacourse", course_service.getAllCourse());
		model.addAttribute("courseForm", course);
		model.addAttribute("formTab","active");//Activa el tab del formulario.
		
		model.addAttribute("editMode",true);//Mira siguiente seccion para mas informacion
		
		return "Course/course-view";
	}
	
	//Recibe POST de Form Editar Curso
	@PostMapping("/editCourse")
	public String postEditCourse(@ModelAttribute("courseForm")Course course, ModelMap model) throws Exception {
		course_service.updateCourse(course);
		return "redirect:/Cursos";
	}
	
	//Listado de Cursos con Profesores
		@GetMapping("/asignar")
		public String asigna(Model model) {				
			
			model.addAttribute("cpForm", new Course());
			model.addAttribute("listacp", course_service.getAllCourse());
			model.addAttribute("listTab","active");
			return "cour_prof/cour-prof-view";
		}
		
	//Carga los valores para editar Cursos y Profesores
	
		@GetMapping("/editcp/{id}")
		public String getEditCP(Model model,@PathVariable(name="id")Long id) throws Exception {
			Course course = course_service.getCourseById(id);		
			
			Iterable<Professor> prof = professor_service.getAllProfessors();
			model.addAttribute("listacp", course_service.getAllCourse());
			model.addAttribute("cpForm", course);
			model.addAttribute("profall", prof);
			model.addAttribute("formTab","active");//Activa el tab del formulario.
			
			model.addAttribute("editMode",true);
			
			return "cour_prof/cour-prof-view";
		}
		
    //Actualiza lista Intermedia Curso-Profesor
		@PostMapping("/editcp")
		public String postEditCP(@ModelAttribute("courseForm")Course cour, ModelMap model) throws Exception {
			crep.save(cour);
			return "redirect:/asignar";
		}
		
		//Boton Cancelar del Formulario
		@GetMapping("cp/cancel")
		public String cancelEditCP(ModelMap model) {
			return "redirect:/asignar";
		}
}
