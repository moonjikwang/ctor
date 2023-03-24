package com.ctor.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SkillLevelDTO {

	private Long no;
	private int java;
	private int javascript;
	private int jsp;
	private int springboot;
	private int htmlcss;
	private int mysql;
	private int jquery;
	private String email;
}
