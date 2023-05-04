package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.bank.Bank;
import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.dtoRequest.BankAccountCreateDto;
import com.joun.sosmall.member.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE bank_account SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BankAccount extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(length = 40, nullable = false)
  private String accountNumber;

  @Column(length = 40, nullable = false)
  private String accountName;

  @ManyToOne
  @JoinColumn(name = "bank_code", nullable = false)
  private Bank bank;

  @Builder
  public BankAccount(int id, Member member, String accountNumber, String accountName, Bank bank) {
    this.id = id;
    this.member = member;
    this.accountNumber = accountNumber;
    this.accountName = accountName;
    this.bank = bank;
  }

  public void setUpdate(BankAccountCreateDto dto) {
    if (dto.getAccountName() != null) {
      this.accountName = dto.getAccountName();
    }
    if (dto.getAccountNumber() != null) {
      this.accountNumber = dto.getAccountNumber();
    }
    if (dto.getBank() != null) {
      this.bank = dto.getBank();
    }
  }

}
