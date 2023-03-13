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
	private String profileImg;
	private LocalDateTime regDate,modDate;
}
