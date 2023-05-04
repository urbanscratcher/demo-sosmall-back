package com.joun.sosmall.dtoResponse;

import java.util.ArrayList;
import java.util.List;

import com.joun.sosmall.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductBasicDto {
  private int id;
  private String name;
  private Integer price;
  private Float discountRate;
  private List<ThumbnailListDto> thumbnails = new ArrayList<>();

  public ProductBasicDto(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.discountRate = product.getDiscountRate();

    if (!product.getThumbnails().isEmpty() && product.getThumbnails().size() != 0) {
      this.thumbnails.add(new ThumbnailListDto(product.getThumbnails().get(0)));
    }

  }
}
