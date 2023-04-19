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
public class Thumbnail extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(columnDefinition = "text", nullable = false)
  private String url;

  @Column(columnDefinition = "TINYINT(1)", nullable = false)
  private int isMain;

}
