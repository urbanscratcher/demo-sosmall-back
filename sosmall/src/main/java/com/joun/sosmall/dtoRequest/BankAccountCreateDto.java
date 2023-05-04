package com.joun.sosmall.dtoRequest;

import com.joun.sosmall.bank.Bank;
import com.joun.sosmall.entity.BankAccount;
import com.joun.sosmall.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
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
