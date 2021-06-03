package com.mjroh.boot.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//X-Frame-Options able
		.headers().frameOptions().disable()
		.and()
		.headers().frameOptions().sameOrigin()
		.and()
		//csrf 토큰 검사 비활성화
		.csrf().disable()
		//acess 제한 허용
		.authorizeRequests()
			//login page는 허용 (안그러면 무한루프에 빠짐)
			.antMatchers("/index", "/static/**")
				.permitAll()
			.antMatchers("/login")
				.permitAll()
			//saveUser page는 허용
			.antMatchers("/user/saveUser", 
					"/user/saveUserAction")
				.permitAll()
			//그 외 모든 접속은 제한됨
			.anyRequest()
				.authenticated()
		.and()
		//로그인하는 경우에 대해 설정함
		.formLogin()
			//로그인 페이지
			.loginPage("/login")
			.loginProcessingUrl("/loginAction")
			.failureUrl("/login?error=true")
			.usernameParameter("email")
			.passwordParameter("pw")
			.failureHandler(new CustomLoginFailureHandler())
			.successHandler(new CustomLoginSeccessHandler())
		.and()
		.logout()
			.logoutUrl("/logoutAction")
			.logoutSuccessUrl("/");
	}
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
