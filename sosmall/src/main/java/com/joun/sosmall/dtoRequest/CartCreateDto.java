package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Cart;
import com.joun.sosmall.entity.MemberStockId;
import com.joun.sosmall.entity.Stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartCreateDto {

  private int memberId;
  private int stockId;
  private int quantity;

  private Stock stock;

  public Cart toEntity() {
    return Cart.builder()
        .id(MemberStockId
            .builder()
            .memberId(this.memberId)
            .stock(this.stock)
            .build())
        .quantity(this.quantity)
        .build();
  }

}
