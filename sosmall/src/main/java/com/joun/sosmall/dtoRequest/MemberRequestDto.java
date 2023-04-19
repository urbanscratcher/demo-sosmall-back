package com.joun.sosmall.dtoRequest;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MemberRequestDto {

  private String name;
  private String password;
  private String email;
  private Boolean isEmailAllowed;
  private String phone;
  private AddressRequestDto address;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthAt;

  public Member ToEntity() {
    return Member.builder()
        .name(this.name)
        .password(this.password)
        .email(this.email)
        .isEmailAllowed(this.isEmailAllowed)
        .phone(this.phone)
        .birthAt(this.birthAt)
        .build();
  }

}
