package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryListDto {
  private int id;
  private String name;

  public ProductCategoryListDto(ProductCategory productCategory) {
    this.id = productCategory.getId();
    this.name = productCategory.getName();
  }

}
