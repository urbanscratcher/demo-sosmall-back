package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.Stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StockCreateDto {
  private int id;
  private int productId;
  private String color;
  private String size;
  private String stockName;
  private int quantity;

  private int soldQuantity;
  private Product product;

  public Stock toEntity() {
    return Stock.builder()
        .id(this.id)
        .product(this.product)
        .color(color)
        .size(this.size)
        .stockName(this.stockName)
        .quantity(this.quantity)
        .soldQuantity(this.soldQuantity)
        .build();
  }
}
