package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Member;

public interface KakaoRepository extends JpaRepository<Member, String>{
	@Query("SELECT a FROM Member a WHERE a.email =:email")
	Member getMemberWithEmail(@Param("email")String email);
	
	List<Member> findByNickName(String nickName);
}
