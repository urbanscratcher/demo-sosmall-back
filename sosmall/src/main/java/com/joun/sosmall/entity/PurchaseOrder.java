package com.joun.sosmall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(indexes = {
    @Index(name = "member_id_idx", columnList = "memberId"),
})
public class PurchaseOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(nullable = false)
  private int memberId;

  @Column(length = 100, nullable = false)
  private String receiverName;

  @Column(length = 20, nullable = false)
  private String receiverPhone;

  @Column(columnDefinition = "char(5)", nullable = false)
  private String receiverZipCode;

  @Column(length = 100, nullable = false)
  private String receiverAddress;

  @Column(length = 100, nullable = false)
  private String receiverAddressDetail;

  @Column(columnDefinition = "text")
  private String deliveryMemo;

  @Column(nullable = false)
  private int totalCost;

  @Column(columnDefinition = "char(3)")
  private String paymentType;

  @Column(length = 40)
  private String accountNumber;

  @Column(length = 40)
  private String accountName;

  @Column
  private int couponId;

  @Column(columnDefinition = "TINYINT(1)", nullable = false)
  @ColumnDefault("0")
  private int isPaid;

  @Column(nullable = false)
  private Date regAt;

  @Column(nullable = false)
  private Date paidAt;

}
