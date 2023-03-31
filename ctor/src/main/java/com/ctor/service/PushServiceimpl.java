package com.ctor.service;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ctor.repository.BlindCommentsRepository;
import com.ctor.repository.BlindRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Service
@RequiredArgsConstructor
public class PushServiceimpl implements PushService{

	@Override
	public void push(String[] targetIds, String title, String body,String url) {
	    
	    	String FLARELANE_API_BASE_URL = "https://api.flarelane.com/v1";
		    String PROJECT_ID = "3c2aa96a-ebf9-4def-85e3-5e7cc9af70d2";
		    String API_KEY = "CVAZuo89OvIaa4-jZEk0Q";
	    	
	    	HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(API_KEY);

	        String POSTurl = FLARELANE_API_BASE_URL + "/projects/" + PROJECT_ID + "/notifications";
	        MessageRequest request = new MessageRequest("userId", targetIds, title, body,url);
	        HttpEntity<MessageRequest> entity = new HttpEntity<>(request, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.exchange(POSTurl, HttpMethod.POST, entity, String.class);

	        System.out.println(response.getBody());
	    }

	    private static class MessageRequest {
	        private String targetType;
	        private String[] targetIds;
	        private String title;
	        private String body;
	        private String url;

	        public MessageRequest(String targetType, String[] targetIds, String title, String body,String url) {
	            this.targetType = targetType;
	            this.targetIds = targetIds;
	            this.title = title;
	            this.body = body;
	            this.url = url;
	        }

	        public String getUrl() {
	        	return url;
	        }
	        public String getTargetType() {
	            return targetType;
	        }

	        public String[] getTargetIds() {
	            return targetIds;
	        }

	        public String getTitle() {
	            return title;
	        }

	        public String getBody() {
	            return body;
	        }
	    }
	
}