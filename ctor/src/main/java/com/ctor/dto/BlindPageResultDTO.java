package com.ctor.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;
/**
 * 
 * @백승연
 * 페이지결과처리DTO
 * 요청된 페이지에 해당하는 글목록을 가져오는 DTO
 * 조회된 글은 컬렉션으로 처리해 리턴
 * 핵심 기능 : JPQL의 결과로 나오는 Object[]을 DTO 타입으로 변환
 * 
 * 1. Page<Entity>의 엔티티 객체들을 DTO 객체로 변환해서 자료구조로 담는다.
 * 2. 화면 출력에 필요한 페이지 정보들을 구성한다.
 * 
 * 다양한 곳에서 사용할 수 있도록 제네릭 타입 이용 -> <DTO, EN> 타입 지정
 * Function<EN, DTO>의 기능: 엔티티 객체들 -> DTO 변환
 */
@Data
public class BlindPageResultDTO<DTO, EN> {

	private List<DTO> dtoList;  	//글 목록을 담은 DTO리스트(컬렉션)
	private int totalPage;			//총 페이지 수
	private int page;				//현재 페이지 번호(번호는 항상 -1이다)
	private int size;				//목록 사이즈
	private int start, end;			//시작페이지 번호, 끝 페이지 번호
	private boolean prev, next;		//이전, 다음
	private List<Integer> pageList; //페이지 번호 목록
	
	//검색시 맵핑되는 키워드 필드 추가
	private String type;			//조건 검색 키워드
	private String keyword;			//검색 키워드
	
	public BlindPageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
		dtoList   = result.stream().map(fn).collect(Collectors.toList());
		totalPage = result.getTotalPages();  
		makePageList(result.getPageable());
	}
	
	/* makePageList()메서드
	 * 페이징 처리를 하기 위한 메서드
	 * 
	 * 1. 페이징 처리를 위해 Pageable 객체를 파라미터로 받는다.
	 * 2. 객체의 메서드를 통해 paging 처리한다.
	 * 
	 * + 공백페이지(Empty 페이지)가 생성될 경우를 고려한 처리 방법
	 * 	 
	 * 	 공(empty)페이지 : 글의 갯수보다 페이지수가 더 많은 것
	 * 	 start page : 1로 고정
	 * 	 end page : 가변
	 * 
	 * 	 DB에 101개의 데이터가 존재한다고 할 때,
	 * 	 (Math.ceil(11/10)) * 10 연산식을 적용하면 -> 결과는 20페이지가 나온다.
	 * 	 실제 글이 담길 페이지는 11페이지만 필요하므로 12~20 까지는 공백페이지로 생성된다.
	 * 	 -> 페이지값(totalPage)을 마지막페이지(end)에 대입하면 -> 11까지가 마지막페이지가 된다.
	 */
	private void makePageList(Pageable pageable) {
		
		this.page = pageable.getPageNumber() + 1; 			//0부터 시작 -> 1추가
		this.size = pageable.getPageSize();
		
		//DB에 있는 목록을 가져와 페이징 index를 생성한다.
		//
		int tempEnd = (int)(Math.ceil(page/5.0)) * 5; 	//temp end page(가변의 endPage)
		
		start = tempEnd - 4; 								//시작은 1 -> -9
		
		prev  = start > 1;	 								//이전페이지를 표현할지 여부
		
		end   = totalPage > tempEnd ? tempEnd : totalPage;	//마지막페이지 : DB에서 가져온 전체페이지 수 & tempEnd를 비교 -> 동적으로 설정 
		
		next  = totalPage > tempEnd;						//다음(next)을 표시할지 여부
		
		pageList = IntStream.rangeClosed(start, end)
				.boxed().collect(Collectors.toList());
	}
}
