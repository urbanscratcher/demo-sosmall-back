package com.joun.sosmall.dtoRequest;

import java.util.ArrayList;
import java.util.List;

import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryCreateDto {

  private int id;
  private ProductCategory parent;
  private List<ProductCategory> children = new ArrayList<ProductCategory>();
  private String name;

  public ProductCategory toEntity() {
    return ProductCategory.builder()
        .id(this.id)
        .parent(this.parent)
        .children(this.children)
        .name(this.name)
        .build();
  }

}
