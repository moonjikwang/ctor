package com.ctor.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctor.dto.BoardDTO;
import com.ctor.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

	private final BoardService boardService;

	@GetMapping("/data")
	public ResponseEntity<List<BoardDTO>> getData(
			@RequestParam(value = "memEmail", required = false) String memEmail,
			@RequestParam(value = "closed", required = false) Boolean closed,
			@RequestParam(value = "techStack", required = false) String techStack,
			@RequestParam(value = "position", required = false) String position) {

		List<BoardDTO> dtoList = boardService.findAllBoards();
		Set<BoardDTO> dtoSet = new HashSet<>(dtoList);

		if (memEmail != null) {
			Set<BoardDTO> tempSet = new HashSet<>();
			boardService.findByEmail(memEmail).forEach(tempDTO -> {
				tempSet.add(tempDTO);
			});
			dtoSet.retainAll(tempSet);
		}
		if (closed != null) {
			Set<BoardDTO> tempSet = new HashSet<>();
			boardService.findByClosed(closed).forEach(tempDTO -> {
				tempSet.add(tempDTO);
			});
			dtoSet.retainAll(tempSet);
		}
		if (techStack != null) {
			Set<BoardDTO> tempSet = new HashSet<>();
			boardService.findByTech(techStack).forEach(tempDTO -> {
				tempSet.add(tempDTO);
			});
			dtoSet.retainAll(tempSet);
		}
		if (position != null) {
			Set<BoardDTO> tempSet = new HashSet<>();
			boardService.findByPosition(position).forEach(tempDTO -> {
				tempSet.add(tempDTO);
			});
			dtoSet.retainAll(tempSet);
		}

		List<BoardDTO> result = new ArrayList<>(dtoSet);
		
		return ResponseEntity.ok(result);

	}
}
