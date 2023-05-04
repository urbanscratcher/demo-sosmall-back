package com.joun.sosmall.dtoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.joun.sosmall.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductListDto extends ProductBasicDto {
  private ProductCategoryListDto productCategory;
  private List<StockDetailDto> stocks = new ArrayList<>();

  public ProductListDto(Product product) {
    super(product);

    this.productCategory = new ProductCategoryListDto(product.getProductCategory());

    this.stocks = product.getStocks().stream().map((o) -> new StockDetailDto(o)).collect(Collectors.toList());
  }
}
