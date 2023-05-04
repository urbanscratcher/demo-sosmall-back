package com.joun.sosmall.member;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.joun.sosmall.address.AddressCreateDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MemberReqDto {

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
        .build();

    if (this.address != null) {
      address.setMember(member);
      member.setAddress(address.toEntity());
    }

    if (this.birthAt != null) {
      member.setBirthAt(birthAt);
    }

    return member;
  }

}
