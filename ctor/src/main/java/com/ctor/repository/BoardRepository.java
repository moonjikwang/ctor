package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ctor.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query("SELECT a FROM Board a WHERE a.member =:email")
	Object[] getBoardByEmail(@Param("email")String email);
	
//	@Query("select b, w from Board b left join b.writer w Where b.bno =:bno") 

	
//	@Query("select b, m from Board AS b left join b.member_email m Where b.member_email =:email")
//	Object[] getBoardByEmail(@Param("email")String email);
//	
//	@Query("select b, w from Board AS b left join b.member w Where b.bno =:bno") 
//	Object getBoardWithWriter(@Param("bno") Long bno); 
}
