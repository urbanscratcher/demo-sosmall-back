package com.joun.sosmall.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity {
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private Date regAt;

  @LastModifiedDate
  @Column(nullable = false)
  private Date modAt;

  @Column
  @Setter
  private Date delAt;

}
