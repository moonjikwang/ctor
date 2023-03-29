package com.ctor.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Member extends BaseEntity{

	@Id
	private String email;
	private String name;
	private String nickName;
	private String grade;
	private String password;
	private String subject;
	private String introduce;
	private String profileImg;
	
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	List<Board> boards = new ArrayList<>();
	@OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
	List<Blind> blinds = new ArrayList<>();
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	List<BoardComments> boardComments = new ArrayList<>();
	@OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
	List<BlindComments> blindsComments = new ArrayList<>();
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	List<Participation> participations = new ArrayList<>();
	@OneToOne(mappedBy = "member", cascade = CascadeType.REMOVE)
	SkillLevel skillLevel = new SkillLevel();
}
