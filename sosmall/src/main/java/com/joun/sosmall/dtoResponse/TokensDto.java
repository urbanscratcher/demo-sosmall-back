package com.joun.sosmall.dtoResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TokensDto {
  private String accessToken;
  private String refreshToken;
}
