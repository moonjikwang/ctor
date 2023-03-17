package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.BlindComments;

public interface BlindCommentsRepository extends JpaRepository<BlindComments, Long>{
	@Modifying
	@Query("delete from BlindComments c where c.blind.blind_no =:blind_no ")
	void deleteBybno(@Param("blind_no")Long blind_no);
}
