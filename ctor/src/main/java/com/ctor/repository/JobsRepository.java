package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Long>{

}
