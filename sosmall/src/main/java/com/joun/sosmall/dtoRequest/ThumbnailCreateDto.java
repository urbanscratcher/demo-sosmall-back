package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.Thumbnail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ThumbnailCreateDto {
  private int productId;
  private String url;
  private Boolean isMain;
  private int listOrder;

  private Product product;

  public Thumbnail toEntity() {
    return Thumbnail.builder()
        .product(this.product)
        .url(this.url)
        .listOrder(this.listOrder)
        .build();
  }
}
