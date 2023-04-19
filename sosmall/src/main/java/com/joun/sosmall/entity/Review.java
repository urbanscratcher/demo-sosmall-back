package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.joun.sosmall.common.BaseTimeEntity;

@Entity
@Table(indexes = {
    @Index(name = "stock_id_idx", columnList = "stockId"),
    @Index(name = "ordered_item_list_id_idx", columnList = "orderedItemListId")
})
public class Review extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(nullable = false)
  private int stockId;

  @Column(nullable = false)
  private int orderedItemListId;

  @Column
  private int star;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

}
