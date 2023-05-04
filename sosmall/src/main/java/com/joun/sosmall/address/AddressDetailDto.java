package com.joun.sosmall.address;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressDetailDto {
  private int id;
  private String zipCode;
  private String address;
  private String addressDetail;
  private Date regAt;
  private Date modAt;

  public AddressDetailDto(Address address) {
    this.id = address.getId();
    this.zipCode = address.getZipCode();
    this.address = address.getAddress();
    this.addressDetail = address.getAddressDetail();
    this.regAt = address.getRegAt();
    this.modAt = address.getModAt();
  }
}
