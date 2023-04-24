package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.common.exception.LogicalConflictException;
import com.joun.sosmall.dtoRequest.StockCreateDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE stock SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false, length = 20)
  private String stockName;

  @Column(columnDefinition = "BINARY(3)")
  @ColumnTransformer(read = "HEX(color)", write = "UNHEX(?)")
  private String color;

  @Column(length = 10)
  private String size;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  @ColumnDefault("0")
  private int soldQuantity;

  @Builder
  public Stock(int id,
      Product product,
      String color, String size, int quantity, int soldQuantity, String stockName) {
    this.id = id;
    this.product = product;
    this.color = color;
    this.size = size;
    this.quantity = quantity;
    this.soldQuantity = soldQuantity;
    this.stockName = stockName;
  }

  public void setUpdate(StockCreateDto dto) {
    this.product = dto.getProduct();
    this.stockName = dto.getStockName();
    this.color = dto.getColor();
    this.size = dto.getSize();
    this.quantity = dto.getQuantity();
    this.soldQuantity = dto.getSoldQuantity();
  }

  public void decreaseQuantity(StockCreateDto dto) throws Exception {

    int restQuantity = this.quantity - dto.getQuantity();

    if (restQuantity <= 0) {
      throw new LogicalConflictException("sold out");
    }

    this.quantity = restQuantity;
    this.soldQuantity += dto.getQuantity();
  }
}
