package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Blind;
import com.ctor.entity.BlindComments;

public interface BlindCommentsRepository extends JpaRepository<BlindComments, Long>{
	//Blind게시물 삭제시 댓글 삭제
	@Modifying
	@Query("delete from BlindComments c where c.blind.bno =:bno ")
	void deleteBybno(@Param("bno")Long bno);
	
	//게시물로 댓글 가져오기
	//List<BlindComments> findByBlind(Long blind_no);
	
	//댓글목록
	List<BlindComments> getCommentsByBlind(Blind blind);
}
