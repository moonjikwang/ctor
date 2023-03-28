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
			boardService.findByEmail(memEmail).forEach(tempDTO -> {
				Set<BoardDTO> tempSet = new HashSet<>();
				tempSet.add(tempDTO);
				dtoSet.retainAll(tempSet);
			});
		}
		if (closed != null) {
			boardService.findByClosed(closed).forEach(tempDTO -> {
				Set<BoardDTO> tempSet = new HashSet<>();
				tempSet.add(tempDTO);
				dtoSet.retainAll(tempSet);
			});
		}
		if (techStack != null) {
			boardService.findByTech(techStack).forEach(tempDTO -> {
				Set<BoardDTO> tempSet = new HashSet<>();
				tempSet.add(tempDTO);
				dtoSet.retainAll(tempSet);
			});
		}
		if (position != null) {
			boardService.findByPosition(position).forEach(tempDTO -> {
				Set<BoardDTO> tempSet = new HashSet<>();
				tempSet.add(tempDTO);
				dtoSet.retainAll(tempSet);
			});
		}

		List<BoardDTO> result = new ArrayList<>(dtoSet);
		
		return ResponseEntity.ok(result);

	}
}
