package com.joun.sosmall.bank;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {

  public Optional<Bank> findByCode(String code);
}
