package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.entity.Bank;
import com.joun.sosmall.repository.BankRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final BankRepository bankRepository;

  public List<Bank> find() {
    return bankRepository.findAll();
  }

  public Optional<Bank> findByCode(String code) {
    return bankRepository.findByCode(code);
  }

}
