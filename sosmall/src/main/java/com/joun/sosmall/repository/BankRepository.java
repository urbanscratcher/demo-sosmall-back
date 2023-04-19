package com.joun.sosmall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

  public Optional<Bank> findByCode(String code);
}
