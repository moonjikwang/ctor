package com.ctor.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	//모든 행 조회(목록)
	@Query(value = "SELECT b, m, COUNT(DISTINCT c), COUNT(DISTINCT p) "
			+ "FROM Board b "
			+ "left join b.member m "
			+ "left join BoardComments c ON c.board = b "
			+ "left join Participation p ON p.board = b "
			+ "GROUP BY b ORDER BY b.closed, b.modDate DESC")
	List<Object[]> getAll();
	
	//선택한 글 조회
	@Query(value = "SELECT b, m, COUNT(DISTINCT c), COUNT(DISTINCT p) "
			+ "FROM Board b "
			+ "left join b.member m "
			+ "left join BoardComments c ON c.board = b "
			+ "left join Participation p ON p.board = b "
			+ "WHERE b.boardno =:bno "
			+ "GROUP BY b, m")
	Tuple getBoardByBoardno(@Param("bno")Long bno);
	
	//작성자Id(email)로 조회
		@Query(value = "SELECT b, m, COUNT(DISTINCT c), COUNT(DISTINCT p) "
				+ "FROM Board b "
				+ "left join b.member m "
				+ "left join BoardComments c ON c.board = b "
				+ "left join Participation p ON p.board = b "
				+ "WHERE b.member.email =:email "
				+ "GROUP BY b ORDER BY b.closed, b.modDate DESC")
		List<Object[]> getBoardByEmail(@Param("email")String email);
	
	//기술스택으로 조회
		@Query(value = "SELECT b, m, COUNT(DISTINCT c), COUNT(DISTINCT p) "
				+ "FROM Board b "
				+ "left join b.member m "
				+ "left join BoardComments c ON c.board = b "
				+ "left join Participation p ON p.board = b "
				+ "WHERE b.techStack like %:tech% "
				+ "GROUP BY b ORDER BY b.closed, b.modDate DESC")
		List<Object[]> getBoardByTech(@Param("tech")String tech);
	
	//직군으로 조회
		@Query(value = "SELECT b, m, COUNT(DISTINCT c), COUNT(DISTINCT p) "
				+ "FROM Board b "
				+ "left join b.member m "
				+ "left join BoardComments c ON c.board = b "
				+ "left join Participation p ON p.board = b "
				+ "WHERE b.position like %:position% "
				+ "GROUP BY b ORDER BY b.closed, b.modDate DESC")
		List<Object[]> getBoardByPosition(@Param("position")String position);

}
