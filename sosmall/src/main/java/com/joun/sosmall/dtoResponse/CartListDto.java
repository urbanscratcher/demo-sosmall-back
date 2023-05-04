package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.Cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartListDto {

  private StockListDto stock;
  private ProductBasicDto product;
  private int quantity;

  public CartListDto(Cart cart) {
    this.stock = new StockListDto(cart.getId().getStock());
    this.product = new ProductBasicDto(cart.getId().getStock().getProduct());
    this.quantity = cart.getQuantity();
  }
}
