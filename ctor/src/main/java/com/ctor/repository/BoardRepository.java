package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	//모든 행 조회
	@Query(value = "SELECT * FROM board b ORDER BY mod_date DESC", nativeQuery = true)
	List<Board> getAll();
	
	//선택한 글 조회
		@Query(value = "SELECT b.* FROM board b WHERE b.boardno =:bno", nativeQuery = true)
		Board getBoardByBoardno(@Param("bno")Long bno);
	
	//작성자Id(email)로 조회
		@Query(value = "SELECT b.* FROM board b WHERE b.member_email =:email ORDER BY closed, mod_date DESC", nativeQuery = true)
		List<Board> getBoardByEmail(@Param("email")String email);
	
	//기술스택으로 조회
		@Query(value = "SELECT b.* FROM board b WHERE b.tech_stack like %:tech% ORDER BY closed, mod_date DESC", nativeQuery = true)
		List<Board> getBoardByTech(@Param("tech")String tech);
	
	//직군으로 조회
		@Query(value = "SELECT b.* FROM board b WHERE b.position like %:position% ORDER BY closed, mod_date DESC", nativeQuery = true)
		List<Board> getBoardByPosition(@Param("position")String position);

}
