package com.ctor.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @백승연
 * BlindDTO의 역할
 * entity 객체의 필요한 속성값을 하나로 만들어서 사용
 *
 * 목록화면에 필요한 데이터
 * 글번호, 제목, 작성자(이메일,닉네임), 댓글수
 * 
 * Member클래스를 참조하지 않고, 화면에서 필요한 작성자의 닉네임을 직접 처리한다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlindDTO {

	private Long blind_no;   				//글넘버
	private String writer; 	 				//작성자 이메일
	private String nickName;				//작성자 닉네임
	private String title;    				//제목
	private String content;  				//내용
	private int reply_count; 				//해당 게시글 댓글 수
	private LocalDateTime regDate,modDate;  //등록일, 수정일
}
