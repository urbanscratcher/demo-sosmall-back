package com.joun.sosmall.dtoRequest;

import java.util.List;
import java.util.stream.Collectors;

import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.Review;
import com.joun.sosmall.entity.ReviewPhoto;
import com.joun.sosmall.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviewCreateDto {

  private int productId;
  private int orderedItemId;
  private int star;
  private String title;
  private String content;
  private List<ReviewPhotoCreateDto> photos;

  private Member member;
  private Product product;

  public Review toEntity() {
    Review review = Review.builder()
        .member(this.member)
        .product(this.product)
        .orderedItemId(this.orderedItemId)
        .star(this.star)
        .title(this.title)
        .content(this.content)
        .build();

    List<ReviewPhoto> reviewPhotos = photos.stream().map((o) -> o.toEntity()).collect(Collectors.toList());
    review.setPhotos(reviewPhotos);

    return review;
  }

}
