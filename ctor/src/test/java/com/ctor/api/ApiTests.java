package com.ctor.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.service.ApiService;

@SpringBootTest
public class ApiTests {
	@Autowired
	ApiService apiService;
	@Test
	public void ApiTests() {

		apiService.apicall();
		
	}
}
