package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
