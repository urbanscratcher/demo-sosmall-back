package com.joun.sosmall.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class BankReqDto {

  private String code;
  private String name;
  private String logoUrl;

  public Bank toEntity() {
    Bank bank = Bank.builder()
        .code(this.code)
        .name(this.name)
        .logoUrl(this.logoUrl)
        .build();

    if (this.logoUrl != null) {
      bank.setLogoUrl(this.logoUrl);
    }

    return bank;
  }

}
