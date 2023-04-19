package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.joun.sosmall.common.BaseTimeEntity;

@Entity
public class Product extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private ProductCategory productCategory;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(columnDefinition = "text")
  private String description;

  @Column(nullable = false)
  private int price;

  @Column
  @ColumnDefault("0")
  private Float discountRate;

}
