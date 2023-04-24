package com.joun.sosmall.dtoRequest;

import java.util.ArrayList;
import java.util.List;

import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.ProductCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductCreateDto {

  private Integer productCategoryId;
  private String name;
  private String description;
  private Integer price;
  private Float discountRate;
  private List<ThumbnailCreateDto> thumbnails = new ArrayList<>();
  private List<StockCreateDto> stocks = new ArrayList<>();

  private ProductCategory productCategory;

  public Product toEntity() {
    Product product = Product.builder()
        .productCategory(this.productCategory)
        .name(this.name)
        .description(this.description)
        .price(this.price)
        .discoutRate(this.discountRate)
        .build();

    if (!this.thumbnails.isEmpty() && this.thumbnails.size() != 0) {
      this.thumbnails.stream().forEach((o) -> {
        o.setProduct(product);
        product.getThumbnails().add(o.toEntity());
      });
    }

    if (!this.stocks.isEmpty() && this.stocks.size() != 0) {
      this.stocks.stream().forEach((o) -> {
        o.setProduct(product);
        product.getStocks().add(o.toEntity());
      });
    }

    return product;
  }
}
