package com.ctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctor.entity.BoardComments;

public interface BoardCommentsRepository extends JpaRepository<BoardComments, Long> {

}
