package com.mjroh.boot.user.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjroh.boot.user.model.entity.MUser;

public interface UserRepository extends JpaRepository<MUser, Long>{
	Optional<MUser> findByEmail(String email);
}
