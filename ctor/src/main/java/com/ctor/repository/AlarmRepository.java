package com.ctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>{

	   List<Alarm> findByMemberEmailAndIsCheckedFalse(String email);
}
