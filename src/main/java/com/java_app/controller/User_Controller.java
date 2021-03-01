package com.java_app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java_app.entity.Course;
import com.java_app.entity.Professor;
import com.java_app.entity.User;
import com.java_app.service.Course_Service;
import com.java_app.service.Professor_Service;
import com.java_app.service.User_Service;

@Controller
public class User_Controller {
	
	@Autowired
	User_Service user_service;
	
	@Autowired
	Course_Service course_service;
	
	@Autowired
	Professor_Service professor_service;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/loginAdmin")
	public String login_admin(
            @RequestParam(value = "username", required = true) String name, 
            @RequestParam(value = "password", required = true) String pass, Model model) 
	{
		try {
			User user=user_service.findBydni(pass);
			if(user.getDni().equals(pass) && user.getLast_name().equals(name)) {
				return "Admin/admin_index";
			}else {
				model.addAttribute("logError","logError");
				return "index";
			}
			
		} catch (Exception e) {
			model.addAttribute("logError","logError");
			return "index";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	

}
