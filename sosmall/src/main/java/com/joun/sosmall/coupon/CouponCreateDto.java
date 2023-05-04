package com.joun.sosmall.coupon;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.joun.sosmall.couponType.CouponType;
import com.joun.sosmall.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CouponCreateDto {
  private int couponTypeId;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date expiredAt;

  private Member member;
  private CouponType couponType;

  public Coupon toEntity() {
    Coupon coupon = Coupon.builder()
        .member(this.member)
        .couponType(this.couponType)
        .expiredAt(this.expiredAt)
        .build();

    return coupon;
  }
}
