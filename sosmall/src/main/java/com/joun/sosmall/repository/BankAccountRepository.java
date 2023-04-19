package com.joun.sosmall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
  public Optional<BankAccount> findByMemberId(int memberId);
}
