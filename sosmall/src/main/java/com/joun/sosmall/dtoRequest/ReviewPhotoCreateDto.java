package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Review;
import com.joun.sosmall.entity.ReviewPhoto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviewPhotoCreateDto {
  private String reviewId;
  private String url;

  private Review review;

  public ReviewPhoto toEntity() {
    return ReviewPhoto.builder()
        .url(this.url)
        .review(this.review)
        .build();
  }
}
