package com.joun.sosmall.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.member.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE address SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(length = 5, nullable = false)
  private String zipCode;

  @Column(length = 100, nullable = false)
  private String address;

  @Setter
  @Column(length = 200)
  private String addressDetail;

  @Builder
  public Address(Member member, int id, String zipCode, String address, String addressDetail, Boolean isMain) {
    this.id = id;
    this.member = member;
    this.zipCode = zipCode;
    this.address = address;
    this.addressDetail = addressDetail;
  }

  public void setUpdate(AddressCreateDto dto) {
    if (dto.getAddress() != null) {
      this.address = dto.getAddress();
    }

    if (dto.getAddressDetail() != null) {
      this.addressDetail = dto.getAddressDetail();
    }

    if (dto.getZipCode() != null) {
      this.zipCode = dto.getZipCode();
    }
  }

}
