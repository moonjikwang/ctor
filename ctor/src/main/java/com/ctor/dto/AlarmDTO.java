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
public class AlarmDTO {

	private Long num;
	private String email;
	private String name;
	private String title;
	private String text;
	private boolean isChecked;
	private LocalDateTime regDate,modDate;
	

}
