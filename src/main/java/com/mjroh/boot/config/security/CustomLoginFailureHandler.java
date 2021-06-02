package com.mjroh.boot.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	private final String DEFAULT_FAILURE_URL = "/login?error=true";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String msg = "";
		
		if(exception instanceof BadCredentialsException) {
			msg = "아이디나 비밀번호가 일치하지 않습니다.";
		}else if(exception instanceof DisabledException) {
			msg = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
		}else if(exception instanceof CredentialsExpiredException) {
			msg = "비밀번호의 유효기간이 만료되었습니다. 관리자에게 문의하세요.";
		}else {
			msg = "알 수 없는 이유로 로그인을 실패하였습니다. 관리자에게 문의하세요.";
		}
		
		request.setAttribute("errorMessage", msg);

		request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);;
	}
}
