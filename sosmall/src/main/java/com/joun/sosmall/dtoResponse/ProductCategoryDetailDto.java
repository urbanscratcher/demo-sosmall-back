package com.joun.sosmall.dtoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryDetailDto extends ProductCategoryListDto {
  private List<ProductCategoryDetailDto> children = new ArrayList<>();
  private int listOrder;

  public ProductCategoryDetailDto(ProductCategory productCategory) {
    super(productCategory);
    this.children = productCategory.getChildren().stream()
        .map(ProductCategoryDetailDto::new)
        .collect(Collectors.toList());
    this.listOrder = productCategory.getListOrder();

  }

}
