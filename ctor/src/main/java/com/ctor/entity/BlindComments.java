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

/**
 * 
 * @백승연
 * BlindComments : 익명게시판 댓글
 * 필드 구성 : 익명댓글넘버, 댓글내용, 댓글작성자
 * M:1관계 -> 블라인드:블라인드코멘츠
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "blind")
public class BlindComments extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cno;		   	   //익명댓글넘버
	
	private String blindCtext;	   //익명댓글내용
	
	private String blindCreplyer;  //익명댓글작성자
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Blind blind;
}