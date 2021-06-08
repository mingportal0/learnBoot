package com.mjroh.boot.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mjroh.boot.user.model.bean.MUserData;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MUserData loginUser = (MUserData)principal;
		session.setAttribute("loginUser", loginUser);
		
		response.sendRedirect("/");
	}

}
