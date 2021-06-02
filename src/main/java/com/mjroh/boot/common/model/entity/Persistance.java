package com.mjroh.boot.common.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Persistance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=1)
	private int isEnabled = 1;
	
	@Column(length=1)
	private int isDeleted = 0;
	
	@Column(length=40)
	@LastModifiedBy
	private String modifier;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createStamp;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifyStamp;
	
}
