package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

  @EmbeddedId
  private MemberStockId id;

  @Column(nullable = false)
  private int quantity;

  @Builder
  public Cart(MemberStockId id, int quantity) {
    this.id = id;
    this.quantity = quantity;
  }

}
