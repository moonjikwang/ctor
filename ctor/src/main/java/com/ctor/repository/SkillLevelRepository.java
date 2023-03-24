package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctor.entity.SkillLevel;

public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long>{
	@Query("select c from SkillLevel c where c.member.email =:email")
	SkillLevel getByEmail(@Param("email")String email);
	
}
