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
  @Column(updatable = false)
  private Date regAt;

  @LastModifiedDate
  private Date modAt;

  @Column(nullable = true)
  @Setter
  private Date delAt;

}
