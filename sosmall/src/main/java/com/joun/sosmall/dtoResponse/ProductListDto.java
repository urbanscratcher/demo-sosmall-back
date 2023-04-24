package com.joun.sosmall.dtoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.joun.sosmall.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductListDto {
  private int id;
  private String name;
  private Integer price;
  private Float discountRate;
  private List<ThumbnailListDto> thumbnails = new ArrayList<>();
  private ProductCategoryListDto productCategory;
  private List<StockDetailDto> stocks = new ArrayList<>();

  public ProductListDto(Product product) {
    this.id = product.getId();
    this.name = product.getName();

    this.price = product.getPrice();
    this.discountRate = product.getDiscountRate();

    this.productCategory = new ProductCategoryListDto(product.getProductCategory());

    this.stocks = product.getStocks().stream().map((o) -> new StockDetailDto(o)).collect(Collectors.toList());

    if (!product.getThumbnails().isEmpty() && product.getThumbnails().size() != 0) {
      this.thumbnails.add(new ThumbnailListDto(product.getThumbnails().get(0)));
    }

  }
}
