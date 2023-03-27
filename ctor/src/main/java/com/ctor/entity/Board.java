package com.ctor.entity;




import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@ToString(exclude = "member")
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardno;	//글번호
	
	private String title;	//글제목
	private String text;	//글내용
	private String category;	//카테고리
	
	private String closingDate;	//모집 마감일
	private String duration;	//프로젝트 기간 (미정, 1개월, 3개월, 6개월, 장기)
	
	private String chatLink; //카카오 오픈챗 링크
	private int groupMember;	//모집인원수
	private int viewCount;		//조회수
	private String position;	//직군
	private String techStack;	//기술스택
	
	private boolean hasTutor;	//멘토링 있는지 여부
	private boolean closed;		//마감여부
	
	
	 @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	 private List<Participation> participation = new ArrayList<>();
	 
	 @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	 private List<BoardComments> BoardComments = new ArrayList<>();
	
	//멤버 ID
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	
	//조회수 카운트 변경
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	//자동마감 처리
	public void setClosed(boolean closing) {
		this.closed = closing;
	}
}
