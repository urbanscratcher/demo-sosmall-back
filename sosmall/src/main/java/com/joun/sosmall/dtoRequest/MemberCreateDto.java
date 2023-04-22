package com.joun.sosmall.dtoRequest;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.joun.sosmall.entity.Address;
import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MemberCreateDto {

  private String name;
  private String password;
  private String email;
  private Boolean isEmailAllowed;
  private String phone;
  private AddressCreateDto address;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthAt;

  public Member toEntity() {
    Member member = Member.builder()
        .name(this.name)
        .password(this.password)
        .email(this.email)
        .isEmailAllowed(this.isEmailAllowed)
        .phone(this.phone)
        .addresses(new ArrayList<Address>())
        .build();

    if (this.address != null) {
      address.setMember(member);
      member.getAddresses().add(address.toEntity());
    }

    if (this.birthAt != null) {
      member.setBirthAt(birthAt);
    }

    return member;
  }

}
