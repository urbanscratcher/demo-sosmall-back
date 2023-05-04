package com.joun.sosmall.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joun.sosmall.member.Member;

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

  @JsonIgnore
  private Member member;

  public Address toEntity() {
    Address address = Address.builder()
        .zipCode(this.zipCode)
        .address(this.address)
        .member(this.member)
        .build();

    if (this.addressDetail != null) {
      address.setAddressDetail(this.addressDetail);
    }

    return address;
  }

}
