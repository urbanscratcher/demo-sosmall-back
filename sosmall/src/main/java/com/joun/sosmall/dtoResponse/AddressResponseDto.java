package com.joun.sosmall.dtoResponse;

import java.util.Date;

import com.joun.sosmall.entity.Address;

import lombok.Getter;

@Getter
public class AddressResponseDto {
  private int id;
  private String zipCode;
  private String address;
  private String addressDetail;
  private Boolean isMain;
  private Date regAt;
  private Date modAt;

  public AddressResponseDto(Address address) {
    this.id = address.getId();
    this.zipCode = address.getZipCode();
    this.address = address.getAddress();
    this.addressDetail = address.getAddressDetail();
    this.isMain = address.getIsMain();
    this.regAt = address.getRegAt();
    this.modAt = address.getModAt();
  }
}
