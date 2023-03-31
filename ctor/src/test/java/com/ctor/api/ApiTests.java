package com.ctor.api;

import java.sql.Array;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctor.service.ApiService;
import com.ctor.service.PushService;

@SpringBootTest
public class ApiTests {
	@Autowired
	PushService pushService;
	@Test
	public void ApiTests() {

		String[] ids = {"moonjikwang@naver.com"};
		pushService.push(ids, "웹푸시 예제입니다.", "웹푸시 예제입니다.");
		
	}
}
