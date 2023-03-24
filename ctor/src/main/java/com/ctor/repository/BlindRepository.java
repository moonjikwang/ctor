package com.ctor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Blind;
import com.ctor.entity.Participation;
/**
 * 
 * @백승연
 * BlindRepository : JPQL 작성
 *   필요한 쿼리 기능 : 목록화면 / 조회화면
 *   목록화면 : 글번호, 제목, 댓글수, 작성자
 *   조회화면 : 글번호, 제목, 내용, 댓글수, 작성자
 * 
 * 목록화면에 필요한 데이터
 *   Blind : 익명게시글넘버, 제목, 작성시간
 *   Member : 닉네임(nickName)
 *   BlindComments : 해당익명글의 댓글수
 * 
 * 조회화면에 필요한 데이터
 *   Blind & Member
 */
public interface BlindRepository extends JpaRepository<Blind, Long>{

	/* 특정 게시물과 해당 게시물을 작성한 회원을 조회 
	   -> 블라인드&멤버 조인 : Blind의 writer변수를 이용해서 조인(연관관계O) */
	@Query("SELECT b, w FROM Blind b LEFT JOIN b.writer w WHERE b.bno =:bno")
	Object getBlindWithWriter(@Param("bno") Long bno);
	
	/* 특정 게시물과 해당 게시물에 속한 댓글들을 조회
	   -> 블라인드는 코멘츠의 객체들을 참조하지 않음
	   -> 블라인드&코멘츠 조인 : ON키워드를 이용한 직접조인(연관관계X) */
	@Query("SELECT b, c FROM Blind b LEFT JOIN BlindComments c ON c.blind = b WHERE b.bno =:bno")
	List<Object[]> getBlindWithComments(@Param("bno") Long bno);
	
	/* 목록화면에 필요한 행 조회(블라인드&멤버&코멘츠 조인)
	   블라인드를 중심으로 group by -> 하나의 라인이 되도록 처리(ON키워드&countQuery)
	   Lazy Fetch를 쓰고 있으므로 중심 테이블인 Blind에 countQuery 명시한다.
	   countQuery를 넣지 않을 경우 -> LazyIntialized 예외 발생 */
	@Query(value = "SELECT b, w, count(c) "
			+ " FROM Blind b "
			+ " LEFT JOIN b.writer w "
			+ " LEFT JOIN BlindComments c ON c.blind = b "
			+ " GROUP BY b",
			countQuery = "SELECT count(b) FROM Blind b")
	//목록화면에 필요한 데이터(페이징)
	Page<Object[]> getBlindWithCommentsCount(Pageable pageable);
	
	// 게시글번호 조회(블라인드&멤버&댓글수 조인)
	@Query("SELECT b, w, count(c) "
			+ " FROM Blind b LEFT JOIN b.writer w "
			+ " LEFT OUTER JOIN BlindComments c ON c.blind = b "
			+ " WHERE b.bno =:bno")
	Object getBlindByBno(@Param("bno") Long bno);
	
	// 닉네임으로 조회
	@Query("SELECT b, w, count(c) "
			+ " FROM Blind b LEFT JOIN b.writer w "
			+ " LEFT OUTER JOIN BlindComments c ON c.blind = b "
			+ " WHERE w.nickName =:nickName")
	Object getBlindByNickname(@Param("nickName") String nickName);
	
	//검색기능
	List<Blind> findByBlindTitleContaining(String keyword);
	
	//검색 후 페이징 처리
	Page<Blind> findByBlindTitleContaining(String keyword, Pageable pageable);
	
	// 사용자가 작성한 익명게시글 조회
	@Query("SELECT b, w, count(c) "
			+ " FROM Blind b LEFT JOIN b.writer w "
			+ " LEFT OUTER JOIN BlindComments c ON c.blind = b "
			+ " WHERE w.nickName =:nickName "
			+ " GROUP BY b ORDER BY b.regDate DESC")
	List<Object[]> findBlindByMemberNickname(@Param("nickName") String nickName);
}
