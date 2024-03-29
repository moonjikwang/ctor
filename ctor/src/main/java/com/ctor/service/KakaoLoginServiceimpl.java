package com.ctor.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.stereotype.Service;

import com.ctor.dto.MemberDTO;
import com.ctor.entity.Adjectives;
import com.ctor.entity.Jobs;
import com.ctor.entity.Member;
import com.ctor.repository.JobsRepository;
import com.ctor.repository.KakaoRepository;
import com.ctor.repository.PositiveAdjectivesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoLoginServiceimpl implements KakaoLoginService {

	@Autowired
	public KakaoRepository kakaoRepository;
    @Autowired
    private PositiveAdjectivesRepository positiveAdjectivesRepository;
    @Autowired
    private JobsRepository jobsRepository;
	@Override
	public String getAccessToken(String authorize_code) throws Exception {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");

			sb.append("&client_id=3ed402e0c9996c2d84bd716afed91789"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&redirect_uri=https://tomcat.jikwang.net/ctor/oauth"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// jackson objectmapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON String -> Map
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			access_Token = jsonMap.get("access_token").toString();
			refresh_Token = jsonMap.get("refresh_token").toString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
			System.out.println("result type" + result.getClass().getName()); // java.lang.String

			try {
				// jackson objectmapper 객체 생성
				ObjectMapper objectMapper = new ObjectMapper();
				// JSON String -> Map
				Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
				});

				System.out.println(jsonMap.get("properties"));

				Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
				Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

				// System.out.println(properties.get("nickname"));
				// System.out.println(kakao_account.get("email"));

				String nickname = properties.get("nickname").toString();
				String profileImg = properties.get("profile_image").toString();
				String email = kakao_account.get("email").toString();

				userInfo.put("profileImg", profileImg);
				userInfo.put("nickname", nickname);
				userInfo.put("email", email);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	@Override
	public MemberDTO findByEmail(String email) {
		Member member = kakaoRepository.getMemberWithEmail(email);
		if(member != null) {
		return entityToDto(member);
		}else {
			return null;
		}
	}
	@Transactional
	@Override
	public String register(MemberDTO dto) {
		String nickname = generateNickname();
	    while (kakaoRepository.findByNickName(nickname).size() != 0) {
            nickname = generateNickname();
        }
	    dto.setNickName(nickname);
	    System.out.println(dto.getNickName());
		Member member = dtoToEntity(dto);
		kakaoRepository.save(member);
		return member.getEmail();
	}

	private String generateNickname() {
		 Random rand = new Random();
	        List<Adjectives> adjectives = positiveAdjectivesRepository.findAll();
	        List<Jobs> jobs = jobsRepository.findAll();
	        Adjectives adjective = adjectives.get(rand.nextInt(adjectives.size()));
	        Jobs job = jobs.get(rand.nextInt(jobs.size()));

	        return adjective.getAdjective() + " " + job.getJob();
	}

	@Override
	public MemberDTO login(String email, String password) {
		Optional<Member> res = kakaoRepository.findById(email);
		if(res.isPresent()) {
		if(res.get() == null) {
			return null;
		}else {
			if(res.get().getPassword().equals(password)) {
				return entityToDto(res.get());
			}else {
				return null;
			}
		}
		}else {
			return null;
		}
		
	}

	@Override
	public void modify(MemberDTO dto) {
		Member member = dtoToEntity(findByEmail(dto.getEmail()));
		member.setIntroduce(dto.getIntroduce());
		kakaoRepository.save(member);
	}

	@Override
	public List<MemberDTO> findAll() {
		List<MemberDTO> result = new ArrayList<>();
		kakaoRepository.findAll().forEach(entity -> result.add(entityToDto(entity)));
		return result;
	}

	@Override
	public void addTeacher(String email) {
		MemberDTO dto = findByEmail(email);
		dto.setGrade("teacher");
		Member member = dtoToEntity(dto);
		kakaoRepository.save(member);
	}

	@Override
	public void deleteById(String email) {
		kakaoRepository.deleteById(email);
		
	}

	

}
