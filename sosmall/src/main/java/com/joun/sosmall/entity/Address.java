package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.dtoRequest.AddressRequestDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE address SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(length = 5, nullable = false)
  private String zipCode;

  @Column(length = 100, nullable = false)
  private String address;

  @Column(length = 200)
  private String addressDetail;

  @Column(columnDefinition = "TINYINT(1)", nullable = false)
  private Boolean isMain;

  @Builder
  public Address(Member member, int id, String zipCode, String address, String addressDetail, Boolean isMain) {
    this.id = id;
    this.member = member;
    this.zipCode = zipCode;
    this.address = address;
    this.addressDetail = addressDetail;
    this.isMain = isMain;
  }

  public void setUpdate(AddressRequestDto dto) {
    if (dto.getAddress() != null) {
      this.address = dto.getAddress();
    }

    if (dto.getAddressDetail() != null) {
      this.addressDetail = dto.getAddressDetail();
    }

    if (dto.getIsMain() != null) {
      this.isMain = dto.getIsMain();
    }

    if (dto.getZipCode() != null) {
      this.zipCode = dto.getZipCode();
    }

  }

}
