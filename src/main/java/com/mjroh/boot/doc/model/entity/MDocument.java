package com.mjroh.boot.doc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;

import com.mjroh.boot.common.model.entity.Persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="mdocument")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper=false)
@Data
public class MDocument extends Persistance {
	@Column(nullable=false, length=500)
	private String title;
	
	@Column(nullable=false, length=5000)
	private String content;
	
	@Column(length=40)
	@CreatedBy
	private String creator;
}
