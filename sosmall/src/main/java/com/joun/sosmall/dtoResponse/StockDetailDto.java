package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.Stock;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockDetailDto {

  private int id;
  private String stockName;
  private String color;
  private String size;
  private int quantity;
  private int soldQuantity;

  public StockDetailDto(Stock stock) {
    this.id = stock.getId();
    this.stockName = stock.getStockName();
    this.color = stock.getColor();
    this.size = stock.getSize();
    this.quantity = stock.getQuantity();
    this.soldQuantity = stock.getSoldQuantity();
  }

}
