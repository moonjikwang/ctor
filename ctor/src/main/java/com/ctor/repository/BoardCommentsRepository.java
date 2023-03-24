package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.BoardComments;
import com.ctor.entity.Member;

public interface BoardCommentsRepository extends JpaRepository<BoardComments, Long> {

	//전체조회 메서드 : 사용 안함
//	@Query(value = "SELECT c "
//			+ "FROM BoardComments c "
//			+ "LEFT JOIN FETCH c.member "
//			+ "LEFT JOIN FETCH c.board "
//			+ "ORDER BY c.regDate DESC")
//	List<BoardComments> getAll();
	
	//원글번호로 글에 딸린 댓글을 조회
	@Query(value = "SELECT c "
			+ "FROM BoardComments c "
			+ "LEFT JOIN FETCH c.member "
			+ "LEFT JOIN FETCH c.board "
			+ "WHERE c.board.boardno =:bno "
			+ "ORDER BY c.regDate DESC")
	List<BoardComments> getByBno(@Param("bno")Long bno);
	
	List<BoardComments> findByMember(Member member);
}
