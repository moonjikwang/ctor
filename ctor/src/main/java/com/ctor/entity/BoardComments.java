package com.ctor.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@ToString(exclude = "boardcomments")
public class BoardComments extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bCommentNo;		   //익명댓글넘버
	
	private String bCommentText;	   //익명댓글내용
	
	private String bCommentWriter; //익명댓글작성자
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;
}