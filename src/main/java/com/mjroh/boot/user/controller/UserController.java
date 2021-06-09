package com.mjroh.boot.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mjroh.boot.user.model.dto.MUserDto;
import com.mjroh.boot.user.model.entity.MUser;
import com.mjroh.boot.user.service.UserRepository;

@Controller
@RequestMapping("/user") 
public class UserController {
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/searchUser") 
	public ModelAndView searchUser() {
		ModelAndView model = new ModelAndView();
		model.addObject("userList", userDao.findAll());
		model.setViewName("user/searchUser");
		return model; 
	}
	
	@RequestMapping(value = "/saveUser")
	public ModelAndView saveDoc() {
		ModelAndView model = new ModelAndView();
		MUser user = MUser.builder().build();
		model.addObject("user", user);
		model.setViewName("user/saveUser");
		return model;
	}
	@RequestMapping(value = "/saveUserAction")
	@ResponseBody
	public ModelAndView saveUserAction(MUser user){
		ModelAndView model = new ModelAndView();
		System.out.println("user = " + user.toString());
		//save
		user.setPw(passwordEncoder.encode(user.getPw()));
		userDao.save(user);
		
		//redirect login page
		model.setViewName("redirect:/login");
		return model;
	}

	@RequestMapping(value = "/viewUser")
	public ModelAndView viewUser(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		MUserDto loginUser = (MUserDto) session.getAttribute("loginUser");
		
		model.addObject("user", loginUser);
		model.setViewName("user/viewUser");
		return model;
	}
}
