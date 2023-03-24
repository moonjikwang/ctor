package com.ctor.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SkillLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	private int java;
	private int javascript;
	private int jsp;
	private int springboot;
	private int htmlcss;
	private int mysql;
	private int jquery;
	
    @OneToOne
    private Member member;
	
}
