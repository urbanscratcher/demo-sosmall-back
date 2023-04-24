package com.joun.sosmall.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.dtoRequest.ProductCreateDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @ManyToOne(targetEntity = ProductCategory.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "product_category_id")
  private ProductCategory productCategory;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(columnDefinition = "text")
  private String description;

  @Column(nullable = false)
  private Integer price;

  @Column
  @ColumnDefault("0")
  private Float discountRate;

  @Setter
  @OneToMany(targetEntity = Stock.class, mappedBy = "product", cascade = { CascadeType.PERSIST,
      CascadeType.REMOVE })
  private List<Stock> stocks = new ArrayList<Stock>();

  @Setter
  @OneToMany(targetEntity = Thumbnail.class, mappedBy = "product", cascade = { CascadeType.PERSIST,
      CascadeType.REMOVE })
  @OrderBy("id ASC")
  private List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();

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

  public void setUpdate(ProductCreateDto dto) {
    this.name = dto.getName();
    this.description = dto.getDescription();
    this.discountRate = dto.getDiscountRate();
    this.price = dto.getPrice();
    this.productCategory = dto.getProductCategory();
  }

}
