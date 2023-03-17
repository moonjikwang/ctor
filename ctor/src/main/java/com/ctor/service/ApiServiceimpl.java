package com.ctor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class ApiServiceimpl implements ApiService{

	@Override
	public String apicall() {
		String reqURL = "https://api.github.com/repos/moonjikwang/ctor/commits";
		URL url;
		try {
			url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("Get");
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		String result = "";

		while ((line = br.readLine()) != null) {
			result += line;
		}
		System.out.println("response body : " + result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
