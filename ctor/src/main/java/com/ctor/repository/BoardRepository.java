package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	//모든 행 조회(목록)
	@Query(value = "SELECT b "
			+ ", COUNT(DISTINCT c) "	
			+ ", COUNT(DISTINCT p) "
			+ "FROM Board b "
			+ "left join BoardComments c ON c.board = b "
			+ "left join Participation p ON p.board = b "
			+ "GROUP BY b  ORDER BY b.closed, b.modDate DESC")
	List<Object[]> getAll();
	
	//선택한 글 조회
		@Query(value = "SELECT b "
				+ ", COUNT(DISTINCT c) "	
				+ ", COUNT(DISTINCT p) "
				+ "FROM Board b "
				+ "left join BoardComments c ON c.board = b "
				+ "left join Participation p ON p.board = b "
				+ "where b.boardno =:bno ")
				
		Object[] getBoardByBoardno(@Param("bno")Long bno);
	
	//작성자Id(email)로 조회
		@Query(value = "SELECT b.*, m.nick_name, m.profile_img, count(c.b_comment_text) "
				+ "FROM board b, member m, board_comments c "
				+ "where b.member_email =:email AND b.boardno = c.board_boardno AND b.member_email = m.email "
				+ "GROUP BY b.boardno ORDER BY closed, mod_date DESC", nativeQuery = true)
		List<Board> getBoardByEmail(@Param("email")String email);
	
	//기술스택으로 조회
		@Query(value = "SELECT b.*, m.nick_name, m.profile_img, count(c.b_comment_text) "
				+ "FROM board b, member m, board_comments c "
				+ "WHERE b.tech_stack like %:tech% AND b.boardno = c.board_boardno AND b.member_email = m.email "
				+ "GROUP BY b.boardno ORDER BY closed, mod_date DESC", nativeQuery = true)
		List<Board> getBoardByTech(@Param("tech")String tech);
	
	//직군으로 조회
		@Query(value = "SELECT b.*, m.nick_name, m.profile_img, count(c.b_comment_text) "
				+ "FROM board b, member m, board_comments c "
				+ "WHERE b.position like %:position% AND b.boardno = c.board_boardno AND b.member_email = m.email "
				+ "GROUP BY b.boardno ORDER BY closed, mod_date DESC", nativeQuery = true)
		List<Board> getBoardByPosition(@Param("position")String position);

}
