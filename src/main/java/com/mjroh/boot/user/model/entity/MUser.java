package com.mjroh.boot.user.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mjroh.boot.common.model.entity.Persistance;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@ToString
@Getter
@Setter
@NoArgsConstructor
public class MUser extends Persistance{
	@Column(nullable=false, length=500)
	private String email;
	
	@Column(nullable=false, length=500)
	private String pw;
	
	@Column(nullable=false, length=200)
	private String name;
	
	@Column(nullable=false, length=13)
	private String phone;
	
	@Column(length=40)
	@Builder.Default
	private String role = "user";
}
