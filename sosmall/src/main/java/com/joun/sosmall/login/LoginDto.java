package com.joun.sosmall.login;

import com.joun.sosmall.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginDto {
  private String email;
  private String password;

  public Member ToEntity() {
    return Member.builder()
        .email(this.email)
        .password(this.password)
        .build();
  }
}
