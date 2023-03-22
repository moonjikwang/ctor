package com.ctor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CtorApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(fileUpload("img/green.png"));
	}

}
