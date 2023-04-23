package com.joun.sosmall.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @Setter
  private ProductCategory parent;

  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "parent")
  @Setter
  private Set<ProductCategory> children = new HashSet<ProductCategory>();

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int listOrder;

  @Builder
  public ProductCategory(int id, ProductCategory parent, Set<ProductCategory> children, String name, int listOrder) {
    this.id = id;
    this.name = name;
    this.parent = parent;
    this.children = children;
    this.listOrder = listOrder;
  }

}
