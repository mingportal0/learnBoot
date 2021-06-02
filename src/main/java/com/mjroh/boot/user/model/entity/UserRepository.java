package com.mjroh.boot.user.model.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MUser, Long>{
	Optional<MUser> findByEmail(String email);
}
