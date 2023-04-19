package com.joun.sosmall.service;

import java.util.List;
import java.util.Optional;

import com.joun.sosmall.entity.Bank;

public interface BankService {
  public List<Bank> find();

  public Optional<Bank> findByCode(String code) throws Exception;
}
