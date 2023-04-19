package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Address;
import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class AddressRequestDto {
  private Member member;
  private String zipCode;
  private String address;
  private String addressDetail;
  private Boolean isMain;

  public AddressRequestDto(String zipCode, String address, String addressDetail, Boolean isMain) {
    this.zipCode = zipCode;
    this.address = address;
    this.addressDetail = addressDetail;
    this.isMain = isMain;
  }

  public Address ToEntity() {
    return Address.builder()
        .zipCode(this.zipCode)
        .address(this.address)
        .addressDetail(this.addressDetail)
        .isMain(this.isMain)
        .member(this.member)
        .build();
  }

}
