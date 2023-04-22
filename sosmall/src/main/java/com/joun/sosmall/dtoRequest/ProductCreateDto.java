package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductCreateDto {

  private ProductCategory productCategory;
  private String name;
  private String description;
  private int price;
  private Float discountRate;

  public Product toEntity(ProductCreateDto dto) {
    return Product.builder()
        .productCategory(this.productCategory)
        .name(this.name)
        .description(this.description)
        .price(this.price)
        .discoutRate(this.discountRate)
        .build();
  }
}
