package com.joun.sosmall.dtoRequest;

import java.util.List;
import java.util.Set;

import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductCategoryCreateDto {
  private int parentId;
  private String name;
  private List<Integer> childrenIds;

  private ProductCategory parent;
  private Set<ProductCategory> children;

  public ProductCategory toEntity() {
    ProductCategory productCategory = ProductCategory.builder()
        .name(this.name)
        .build();

    if (this.children != null && !this.children.isEmpty()) {
      productCategory.setChildren(this.children);
    }

    if (this.parent != null) {
      productCategory.setParent(this.parent);
    }

    return productCategory;
  }
}
