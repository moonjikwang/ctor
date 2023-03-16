package com.ctor.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
/**
 * 
 * @백승연
 * 페이지요청처리DTO : 화면에 필요한 목록 데이터의 처리를 위한 DTO 
 * 
 * 1. 화면에서 필요한 목록 데이터에 대한 DTO 생성
 * 2. DTO를 Pageable 타입으로 전환
 * 3. Page<Entity>를 화면에서 사용하기 쉬운 DTO의 리스트로 변환
 * 4. 화면에 필요한 페이지 번호 처리
 */
@Builder
@AllArgsConstructor
@Data
public class BlindPageRequestDTO {

	private int page;		//요청되는 페이지 번호
	private int size; 		//요청되는 페이지 목록수
	
	//검색시 맵핑되는 키워드 필드
	private String type;	//조건검색 키워드
	private String keyword;	//검색 키워드
	
	//페이지 번호 기본값 설정
	public BlindPageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page -1, size, sort);
		//기본적으로 리턴되는 페이지 인덱스 : 0
		//0이 1페이지가 되도록 page -1
		//sort : 목적에 따라 변경할 수 있게 파라미터로 넘긴다.(descending, ascending etc...)
	}
}
