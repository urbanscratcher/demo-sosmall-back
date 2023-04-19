package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {
    @Index(name = "member_id_idx", columnList = "memberId"),
    @Index(name = "product_id_idx", columnList = "productId")
})
public class Interest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(nullable = false)
  private int memberId;

  @Column(nullable = false)
  private int productId;

}
