package com.mjroh.boot.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/") 
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) { 
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model; 
	}
	
	@RequestMapping("/index") 
	public ModelAndView index() { 
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model; 
	}
	
	@RequestMapping("/login") 
	public ModelAndView login() { 
		ModelAndView model = new ModelAndView();
		model.setViewName("user/login");
		return model; 
	}
}
