package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.Member;

public interface KakaoRepository extends JpaRepository<Member, String>{

}
