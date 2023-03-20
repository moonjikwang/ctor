package com.ctor.blindComments;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.dto.BlindCommentsDTO;
import com.ctor.service.BlindCommentsService;

@SpringBootTest
public class BlindCommentsServiceTests {

	@Autowired
	private BlindCommentsService service;
	
	@Test
	public void getListTest() {
		Long bno = 10L;
		
		List<BlindCommentsDTO> bcDTOList = service.getList(bno);
		bcDTOList.forEach(blindCommentsDTO -> System.out.println(blindCommentsDTO));
	}
}
