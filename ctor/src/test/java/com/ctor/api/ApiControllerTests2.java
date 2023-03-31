package com.ctor.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ctor.service.ApiService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTests2 {
	//제공할 API컨트롤러 테스트
	 @Autowired
	 private MockMvc mockMvc;
	 
	@Test
	public void ApiTests() throws Exception {

		mockMvc.perform(
				 get("/api/data")
	                // param -> api테스트할때 사용될 요청 파라미터를 설정한다. (단 값은 string만 허용되므로 숫자나 날짜는 문자열로 변경후 사용)
	                .param("position", "프론트엔드")
	                .param("techStack", "JAVA")
//	                .param("memEmail", "yh999886@gmail.com")
				 )
				.andDo(print());
		
	}
}
