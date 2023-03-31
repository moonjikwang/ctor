package com.ctor.service;

import org.springframework.stereotype.Service;

import com.ctor.dto.AlarmDTO;
import com.ctor.entity.Alarm;
import com.ctor.entity.Member;
import com.ctor.repository.AlarmRepository;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {

	private final AlarmRepository alarmRepository;
	
	public void addAlarm(AlarmDTO dto) {
		alarmRepository.save(dtoToEntity(dto));
	};
	
	private Alarm dtoToEntity(AlarmDTO dto) {
		return Alarm.builder().isChecked(dto.isChecked())
						.title(dto.getTitle())
						.text(dto.getText())
						.member(Member.builder().email(dto.getEmail()).build())
						.build();
	}
	private AlarmDTO entityToDTO(Alarm entity) {
		return AlarmDTO.builder().isChecked(entity.isChecked())
				.title(entity.getTitle())
				.text(entity.getText())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.num(entity.getNum())
				.email(entity.getMember().getEmail())
				.build();
	}
	
}
