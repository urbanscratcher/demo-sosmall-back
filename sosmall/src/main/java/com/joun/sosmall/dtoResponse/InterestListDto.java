package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.Interest;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestListDto {
  private ProductBasicDto product;

  public InterestListDto(Interest interest) {
    this.product = new ProductBasicDto(interest.getId().getProduct());
  }
}
