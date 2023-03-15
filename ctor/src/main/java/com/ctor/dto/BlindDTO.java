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
public class BlindDTO {

	private String writer;
	private String title;
	private String content;
	private LocalDateTime regDate,modDate;
}
