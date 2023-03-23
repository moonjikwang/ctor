package com.ctor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctor.dto.BlindCommentsDTO;
import com.ctor.entity.Blind;
import com.ctor.entity.BlindComments;
import com.ctor.repository.BlindCommentsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlindCommentsServiceImpl implements BlindCommentsService{

	private final BlindCommentsRepository bCRepository;

	@Override
	public Long register(BlindCommentsDTO blindCommentsDTO) {
		BlindComments bComments = dtoToEntity(blindCommentsDTO);
		bCRepository.save(bComments);
		return bComments.getCno();
	}

	@Override
	public List<BlindCommentsDTO> getList(Long bno) {
		List<BlindComments> result = bCRepository
				.getCommentsByBlind(Blind.builder().bno(bno).build());
		
		return result.stream().map(blindComments -> entityToDTO(blindComments))
				.collect(Collectors.toList());
	}

	@Override
	public void modify(BlindCommentsDTO blindCommentsDTO) {
		BlindComments blindComments = dtoToEntity(blindCommentsDTO);
		bCRepository.save(blindComments);
	}

	@Override
	public void remove(Long cno) {
		bCRepository.deleteById(cno);
	}

	@Override
	public BlindCommentsDTO getDTO(Long cno) {
		return entityToDTO(bCRepository.findById(cno).get());
	}
	
}
