package com.joun.sosmall.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class MemberStockId implements Serializable {
  private int memberId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stock_id", nullable = false)
  private Stock stock;

  @Builder
  public MemberStockId(int memberId,
      Stock stock) {
    this.memberId = memberId;
    this.stock = stock;
  }
}
