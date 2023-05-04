package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Interest;
import com.joun.sosmall.entity.MemberProductId;
import com.joun.sosmall.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class InterestCreateDto {

  private int memberId;
  private int productId;

  private Product product;

  public Interest toEntity() {
    return Interest.builder()
        .id(MemberProductId
            .builder()
            .memberId(this.memberId)
            .product(this.product)
            .build())
        .build();
  }
}
