package com.joun.sosmall.dtoResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joun.sosmall.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailDto extends ProductListDto {
  private String description;
  private ProductCategoryDetailDto productCategory;
  private List<ThumbnailListDto> thumbnails = new ArrayList<>();

  private Date regAt;
  private Date modAt;

  public ProductDetailDto(Product product) {
    super(product);
    this.description = product.getDescription();

    this.regAt = product.getRegAt();
    this.modAt = product.getModAt();

    this.productCategory = new ProductCategoryDetailDto(product.getProductCategory());

    if (!product.getThumbnails().isEmpty() && product.getThumbnails().size() != 0) {

      product.getThumbnails().stream().forEach((o) -> {
        this.thumbnails.add(new ThumbnailListDto(o));
      });

    }

  }
}
