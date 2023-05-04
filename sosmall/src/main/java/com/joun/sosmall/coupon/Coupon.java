package com.joun.sosmall.coupon;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.couponType.CouponType;
import com.joun.sosmall.member.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE coupon SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne
  @JoinColumn(name = "coupon_type_id", nullable = false)
  private CouponType couponType;

  @Column(nullable = false)
  private Date expiredAt;

  @Builder
  public Coupon(int id, Member member, CouponType couponType, Date expiredAt) {
    this.id = id;
    this.member = member;
    this.couponType = couponType;
    this.expiredAt = expiredAt;
  }

}
