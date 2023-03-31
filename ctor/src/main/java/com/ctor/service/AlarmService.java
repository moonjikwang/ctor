package com.ctor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ctor.dto.AlarmDTO;
import com.ctor.entity.Alarm;
import com.ctor.entity.Member;
import com.ctor.repository.AlarmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {

	private final AlarmRepository alarmRepository;
	
	public void addAlarm(AlarmDTO dto) {
		alarmRepository.save(dtoToEntity(dto));
	};
	public List<AlarmDTO> findbyEmail(String email){
		List<AlarmDTO> result = new ArrayList<>();
		alarmRepository.findByMemberEmailAndIsCheckedFalse(email).forEach(entity -> result.add(entityToDTO(entity)));
		return result;
		
	};
	public void checkedAlarm(long num) {
		Alarm entity = alarmRepository.findById(num).get();
		entity.setIsChecked(true);
		alarmRepository.save(entity);
	}
	
	private Alarm dtoToEntity(AlarmDTO dto) {
		return Alarm.builder().isChecked(dto.isChecked())
						.title(dto.getTitle())
						.num(dto.getNum())
						.text(dto.getText())
						.url(dto.getUrl())
						.member(Member.builder().email(dto.getEmail()).build())
						.build();
	}
	private AlarmDTO entityToDTO(Alarm entity) {
		return AlarmDTO.builder().isChecked(entity.isChecked())
				.title(entity.getTitle())
				.text(entity.getText())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.url(entity.getUrl())
				.num(entity.getNum())
				.email(entity.getMember().getEmail())
				.build();
	}
	
}
