package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>{

}
