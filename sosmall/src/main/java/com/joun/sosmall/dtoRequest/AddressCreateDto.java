package com.joun.sosmall.dtoRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joun.sosmall.entity.Address;
import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddressCreateDto {
  private String zipCode;
  private String address;
  private String addressDetail;
  private Boolean isMain;

  @JsonIgnore
  private Member member;

  public Address toEntity() {
    Address address = Address.builder()
        .zipCode(this.zipCode)
        .address(this.address)
        .isMain(this.isMain)
        .member(this.member)
        .build();

    if (this.addressDetail != null) {
      address.setAddressDetail(this.addressDetail);
    }

    return address;
  }

}
