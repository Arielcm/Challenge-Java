package com.java_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java_app.entity.Course;
import com.java_app.entity.Professor;
import com.java_app.entity.Student;
import com.java_app.repository.Professor_Repository;
import com.java_app.repository.Student_Repository;
import com.java_app.service.Course_Service;
import com.java_app.service.Student_Service;

@Controller
public class Student_Controller {

	@Autowired
	Student_Service student_service;
	
	@Autowired
	Course_Service course_service;
	
	@Autowired
	Professor_Repository prof_rep;
	
	@Autowired
	Student_Repository stud_rep;
	

	@PostMapping("/loginStudent")
	public String login_student(
            @RequestParam(value = "username", required = true) Long name, 
            @RequestParam(value = "password", required = true) String pass, Model model) 
	{
		
		try {
		Student student=student_service.findBydni(pass);
		if(student.getDni().equals(pass) && student.getFile() == name) {
			model.addAttribute("studCon", student);
			return "Student/student_index";
		}else {
			model.addAttribute("logError","logError");
			return "index";
		}
	} catch (Exception e) {
		model.addAttribute("logError","logError");
		return "index";
	}
	}
	
	@GetMapping("/student/{id}")
	public String student(Model model, @PathVariable(name="id")Long id) throws Exception {
		Student stud = student_service.getStudentById(id);
		model.addAttribute("studForm", new Student());
		model.addAttribute("studCon", stud);
		model.addAttribute("listTab","active");
		return "Student/stud-view";
	}
	
	//Boton Cancelar del Formulario
	@GetMapping("student/cancel/{id}")
	public String cancelEditCP(ModelMap model,@PathVariable(name="id")Long id) {

		return "redirect:/student/"+id;
	}
	
	//Carga los valores para inscribir a materias
	
	@GetMapping("/editstud/{id}")
	public String getEditstud(Model model,@PathVariable(name="id")Long id) throws Exception {
		Iterable<Course> cour = course_service.getAllCourse();
		Student stud = student_service.getStudentById(id);
		model.addAttribute("studCon", stud);
		model.addAttribute("studForm", stud);
		model.addAttribute("matall", cour);
		model.addAttribute("formTab","active");//Activa el tab del formulario.
		
		model.addAttribute("editMode",true);

		return "Student/stud-view";
	}
	
    //Actualiza lista Intermedia Student-Curso
		@PostMapping("/editstud")
		public String postEditCP(@ModelAttribute("courseForm")Student stud, ModelMap model) throws Exception {
			stud_rep.save(stud);
			Long id = stud.getId();
			return "redirect:/student/"+id;
		}
	
	
}
