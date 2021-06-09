package com.mjroh.boot.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mjroh.boot.user.model.dto.MUserDto;
import com.mjroh.boot.user.model.entity.MUser;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username){
		MUser user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("아이디가 일치하지 않습니다."));
		return new MUserDto(user);
	}
}