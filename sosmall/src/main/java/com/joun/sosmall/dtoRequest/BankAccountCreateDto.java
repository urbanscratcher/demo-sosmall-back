package com.joun.sosmall.dtoRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joun.sosmall.entity.Bank;
import com.joun.sosmall.entity.BankAccount;
import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class BankAccountCreateDto {
  private String bankCode;
  private String accountNumber;
  private String accountName;

  private Member member;
  private Bank bank;

  public BankAccount toEntity() {
    return BankAccount.builder()
        .accountNumber(this.accountNumber)
        .accountName(this.accountName)
        .bank(this.bank)
        .member(this.member)
        .build();
  }
}
