package com.ctor.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
abstract class BaseEntity {
	@CreatedDate
	@Column(name = "regDate",updatable = false)
	private LocalDateTime regDate;
	@LastModifiedDate
	@Column(name = "modDate")
	private LocalDateTime modDate;
}
