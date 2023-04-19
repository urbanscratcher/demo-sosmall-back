package com.joun.sosmall.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.DataNotFoundException;
import com.joun.sosmall.entity.Bank;
import com.joun.sosmall.serviceImpl.BankServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RefController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final BankServiceImpl bankService;

  @GetMapping("/banks")
  public List<Bank> findBanks() {
    return bankService.find();
  }

  @GetMapping("/banks/{code}")
  public Bank findBankByCode(@PathVariable String code) throws Exception {
    Optional<Bank> bank = bankService.findByCode(code);

    if (!bank.isPresent()) {
      throw new DataNotFoundException();
    }
    return bank.get();
  }

}
