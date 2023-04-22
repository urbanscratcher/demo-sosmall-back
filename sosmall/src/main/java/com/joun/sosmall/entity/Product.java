package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE product SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

  @Builder
  public Product(int id, ProductCategory productCategory, String name, String description, int price,
      Float discoutRate) {
    this.id = id;
    this.productCategory = productCategory;
    this.name = name;
    this.description = description;
    this.price = price;
    this.discountRate = discoutRate;
  }

  // public void setUpdate(ProductCreateDto dto){

  // }

}
