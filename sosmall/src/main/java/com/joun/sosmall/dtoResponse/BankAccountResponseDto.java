package com.joun.sosmall.dtoResponse;

import java.util.Date;

import com.joun.sosmall.entity.Bank;
import com.joun.sosmall.entity.BankAccount;

import lombok.Getter;

@Getter
public class BankAccountResponseDto {
  private int id;
  private String accountNumber;
  private String accountName;
  private Bank bank;
  private Date regAt;
  private Date modAt;

  public BankAccountResponseDto(BankAccount bankAccount) {
    this.id = bankAccount.getId();
    this.accountNumber = bankAccount.getAccountNumber();
    this.accountName = bankAccount.getAccountName();
    this.bank = bankAccount.getBank();
    this.regAt = bankAccount.getRegAt();
    this.modAt = bankAccount.getModAt();
  }
}
