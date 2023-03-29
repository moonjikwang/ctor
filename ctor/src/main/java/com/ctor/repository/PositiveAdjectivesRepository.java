package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.Adjectives;

public interface PositiveAdjectivesRepository extends JpaRepository<Adjectives, Long>{

}
