package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CouponType {

  @Id
  @Column(columnDefinition = "char(3)")
  private String code;

  @Column(nullable = false, length = 20)
  private String name;

  @Column
  private Float discountRate;
}
