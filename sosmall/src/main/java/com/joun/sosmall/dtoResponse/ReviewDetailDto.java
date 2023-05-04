package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.Review;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDetailDto extends ReviewListDto {
  private String content;

  public ReviewDetailDto(Review review) {
    super(review);
    this.content = review.getContent();
  }

}
