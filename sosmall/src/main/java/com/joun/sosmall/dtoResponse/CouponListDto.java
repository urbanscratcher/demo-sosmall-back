package com.joun.sosmall.dtoResponse;

import java.util.Date;

import com.joun.sosmall.entity.Coupon;
import com.joun.sosmall.entity.CouponType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CouponListDto {
  private int id;
  private CouponType couponType;
  private Date expiredAt;
  private Date regAt;
  private Date modAt;

  public CouponListDto(Coupon coupon) {
    this.id = coupon.getId();
    this.couponType = coupon.getCouponType();
    this.expiredAt = coupon.getExpiredAt();
    this.regAt = coupon.getRegAt();
    this.modAt = coupon.getModAt();
  }
}
