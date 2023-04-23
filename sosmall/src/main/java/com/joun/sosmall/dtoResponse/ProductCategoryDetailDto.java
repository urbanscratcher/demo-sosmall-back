package com.joun.sosmall.dtoResponse;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryDetailDto {
  private int id;
  private String name;
  private Set<ProductCategoryDetailDto> children = new HashSet<>();

  public ProductCategoryDetailDto(ProductCategory productCategory) {
    this.id = productCategory.getId();
    this.name = productCategory.getName();
    this.children = productCategory.getChildren().stream().map(ProductCategoryDetailDto::new)
        .collect(Collectors.toSet());
  }

}
