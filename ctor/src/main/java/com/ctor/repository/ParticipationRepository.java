package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

	
}
