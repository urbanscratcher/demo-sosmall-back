package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.Stock;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockDetailDto extends StockListDto {

  private ProductBasicDto product;

  public StockDetailDto(Stock stock) {
    super(stock);
    this.product = new ProductBasicDto(stock.getProduct());
  }

}
