package com.mjroh.boot.doc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.annotation.CreatedBy;

import com.mjroh.boot.common.model.entity.Persistance;

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
public class MDocument extends Persistance {
	@Column(nullable=false, length=500)
	private String title;
	
	@Column(nullable=false, length=5000)
	private String content;
	
	@Column(length=40)
	@CreatedBy
	private String creator;
}
