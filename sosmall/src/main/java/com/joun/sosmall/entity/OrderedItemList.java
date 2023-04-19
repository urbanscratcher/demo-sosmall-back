package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderedItemList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne
  @JoinColumn(name = "purchase_order_id", nullable = false)
  private PurchaseOrder purchaseOrder;

  @ManyToOne
  @JoinColumn(name = "ordered_item_id", nullable = false)
  private OrderedItem orderedItem;

}
