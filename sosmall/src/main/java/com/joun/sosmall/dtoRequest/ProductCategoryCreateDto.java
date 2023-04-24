package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryCreateDto {
  private int parentId;
  private String name;
  private int listOrder;

  private ProductCategory parent;

  public ProductCategory toEntity() {
    ProductCategory productCategory = ProductCategory.builder()
        .name(this.name)
        .listOrder(this.listOrder)
        .build();

    if (this.parent != null) {
      productCategory.setParent(this.parent);
    }

    return productCategory;
  }
}
