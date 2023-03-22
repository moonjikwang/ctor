package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

	//원글번호로 글에 딸린 참여자를 조회
		@Query(value = "SELECT p "
				+ "FROM Participation p "
				+ "LEFT JOIN FETCH p.member "
				+ "LEFT JOIN FETCH p.board "
				+ "WHERE p.board.boardno =:bno "
				+ "ORDER BY p.regDate DESC")
		List<Participation> getByBno(@Param("bno")Long bno);
		
	//회원이 참여중인 내용을 조회
		@Query(value = "SELECT p "
				+ "FROM Participation p "
				+ "LEFT JOIN FETCH p.member "
				+ "LEFT JOIN FETCH p.board "
				+ "WHERE p.member.email =:email "
				+ "ORDER BY p.regDate DESC")
		List<Participation> getByEmail(@Param("email")String email);
	
		
}
