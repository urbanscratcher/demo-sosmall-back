package com.joun.sosmall.coupon;

import java.util.Date;

import com.joun.sosmall.couponType.CouponType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
