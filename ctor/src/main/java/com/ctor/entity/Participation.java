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
 * @author 이유현
 * 참여 신청 엔티티
 * 목록/상세페이지에서 [신청]버튼 클릭하면 테이블에 신청서 row 추가
 *
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "participation")
public class Participation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pNo;	//신청서 PK

	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;	//신청한 프로젝트/스터디

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;	//신청자 
}