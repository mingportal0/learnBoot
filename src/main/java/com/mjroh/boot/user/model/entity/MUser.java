package com.mjroh.boot.user.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mjroh.boot.common.model.entity.Persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="muser")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper=false)
@Data
public class MUser extends Persistance{
	@Column(nullable=false, length=40)
	private String email;
	
	@Column(nullable=false, length=40)
	private String pw;
	
	@Column(nullable=false, length=60)
	private String name;
	
	@Column(nullable=false, length=13)
	private String phone;
	
	@Column(length=40)
	private String role = "user";
}
