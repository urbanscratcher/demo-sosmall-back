package com.joun.sosmall.couponType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CouponTypeReqDto {
  private String name;
  private Float discountRate;

  public CouponType ToEntity() {
    return CouponType.builder()
        .name(this.name)
        .discountRate(this.discountRate)
        .build();
  }
}
