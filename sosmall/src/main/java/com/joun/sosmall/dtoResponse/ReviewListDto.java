package com.joun.sosmall.dtoResponse;

import java.util.Date;

import com.joun.sosmall.entity.Review;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewListDto {

  private int id;
  private int memberId;
  private int productId;
  private String memberName;
  private int star;
  private String title;
  private Date regAt;
  private Date modAt;

  public ReviewListDto(Review review) {
    this.id = review.getId();
    this.productId = review.getProduct().getId();
    this.memberId = review.getMember().getId();
    this.memberName = review.getMember().getName();
    this.star = review.getStar();
    this.title = review.getTitle();
    this.regAt = review.getRegAt();
    this.modAt = review.getModAt();
  }
}
