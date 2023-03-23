package com.ctor.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	private String email;
	private String name;
	private String grade;
	private String subject;
	private String introduce;
	private String password;
	private String nickName;
	private String profileImg;
	private LocalDateTime regDate,modDate;
}
