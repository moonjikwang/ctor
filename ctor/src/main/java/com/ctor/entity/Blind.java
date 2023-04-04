 package com.ctor.entity;

import javax.persistence.Column;
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
 * @백승연
 * Blind : 익명게시판 엔티티
 * 저장 컬럼 : 글넘버, 제목, 내용
 * M:1관계 -> 멤버:블라인드 
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Blind extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;		  	 //글번호
	private String blindTitle;   //게시글 제목
	private String blindContent; //게시글 내용
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private Long viewCount;		 //조회수
	
	//Member 클래스와 M:1 관계설정
	@ManyToOne(fetch = FetchType.LAZY)
	private Member writer;
	
	//조회수 카운트 변경
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	
	public void changeTitle(String blindTitle) {
		this.blindTitle = blindTitle;
	}
	
	public void changeContent(String blindContent) {
		this.blindContent = blindContent;
	}
}