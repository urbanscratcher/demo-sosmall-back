package com.joun.sosmall.dtoResponse;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.joun.sosmall.entity.Address;
import com.joun.sosmall.entity.Member;

import lombok.Getter;

@Getter
public class MemberResponseDto {
  private int id;
  private String name;
  private String email;
  private Boolean isEmailAllowed;
  private String phone;
  private Date birthAt;
  private List<AddressResponseDto> addresses;
  private Date regAt;
  private Date modAt;

  public MemberResponseDto(Member member) {
    this.id = member.getId();
    this.name = member.getName();
    this.email = member.getEmail();
    this.isEmailAllowed = member.getIsEmailAllowed();
    this.phone = member.getPhone();
    this.birthAt = member.getBirthAt();

    List<Address> addresses = member.getAddresses();
    this.addresses = addresses.stream().map(a -> new AddressResponseDto(a))
        .collect(Collectors.toList());

    this.regAt = member.getRegAt();
    this.modAt = member.getModAt();
  }

}
