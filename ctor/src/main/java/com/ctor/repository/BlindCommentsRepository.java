package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.Blind;
import com.ctor.entity.BlindComments;

public interface BlindCommentsRepository extends JpaRepository<BlindComments, Long>{
	@Modifying
	@Query("delete from BlindComments c where c.blind.blind_no =:blind_no ")
	void deleteBybno(@Param("blind_no")Long blind_no);
	
	//게시물로 댓글 가져오기
	List<BlindComments> findByBlindBno(Long blind_no);
	
	//댓글목록
	List<BlindComments> getCommentsByBlindOrderByCno(Blind blind);
}
