package com.mjroh.boot.user.model.dto;

import java.util.Arrays;
import java.util.Date;

import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mjroh.boot.user.model.entity.MUser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MUserDto extends User{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String role;
	private Long id;
	private int isEnabled;
	private int isDeleted;
	private String modifier;
	private Date createStamp;
	private Date modifyStamp;

	public MUserDto(MUser user) {
		super(user.getEmail(), user.getPw(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
		this.email = user.getEmail();
		this.pw = user.getPw();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.role = user.getRole();
		this.id = user.getId();
		this.isEnabled = user.getIsEnabled();
		this.isDeleted = user.getIsDeleted();
		this.modifier = user.getModifier();
		this.createStamp = user.getCreateStamp();
		this.modifyStamp = user.getModifyStamp();
	}
	
	public MUser getMUser(MUserDto dto) {
		MUser user = MUser.builder()
				.email(email)
				.pw(pw)
				.name(name)
				.phone(phone)
				.role(role)
				.id(id)
				.isEnabled(isEnabled)
				.isDeleted(isDeleted)
				.modifier(modifier)
				.createStamp(createStamp)
				.modifyStamp(modifyStamp)
				.build();
		return user;
	}
}
