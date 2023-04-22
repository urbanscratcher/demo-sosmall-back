package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginDto {
  private String email;
  private String password;

  public Member toEntity() {
    return Member.builder()
        .email(this.email)
        .password(this.password)
        .build();
  }
}
