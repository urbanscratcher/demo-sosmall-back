package com.joun.sosmall.couponType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false)
  private Float discountRate;

  @Builder
  public CouponType(int id, String name, Float discountRate) {
    this.id = id;
    this.name = name;
    this.discountRate = discountRate;
  }

}
