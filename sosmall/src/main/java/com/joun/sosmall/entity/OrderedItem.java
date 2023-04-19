package com.joun.sosmall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderedItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(nullable = false)
  private int quantity;

  @Column(columnDefinition = "char(1)", nullable = false)
  private String purchaseStatus;

  @Column(nullable = false)
  private Date regAt;

  @Column(nullable = false)
  private Date modAt;

}
