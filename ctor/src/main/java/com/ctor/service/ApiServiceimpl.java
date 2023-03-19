package com.ctor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


@Service
public class ApiServiceimpl implements ApiService{

	@Override
	public List<Map<String, String>> apicall() {
		String reqURL = "https://api.github.com/repos/moonjikwang/ctor/commits?per_page=100";
		URL url;
		List<Map<String, String>> resultList =  new ArrayList<>();
		try {
			url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		String result = "";

		while ((line = br.readLine()) != null) {
			result += line;
		}
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(result);
        for(int i=0 ; i<jsonArray.size() ; i++){
        	Map<String, String> map = new HashMap<>();
            JSONObject committer = (JSONObject) ((JSONObject) jsonArray.get(i)).get("commit");
            map.put("name", ((JSONObject)committer.get("committer")).get("name").toString());
            map.put("date", ((JSONObject)committer.get("committer")).get("date").toString());
            map.put("message", committer.get("message").toString());
            resultList.add(map);
        }
		
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

	
}
