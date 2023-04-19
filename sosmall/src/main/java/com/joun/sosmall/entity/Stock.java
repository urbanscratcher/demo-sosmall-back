package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.joun.sosmall.common.BaseTimeEntity;

@Entity
public class Stock extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(columnDefinition = "char(7)")
  private int color;

  @Column(length = 10)
  private String size;

  @Column
  private int quantity;

  @Column
  private int soldQuantity;

}
