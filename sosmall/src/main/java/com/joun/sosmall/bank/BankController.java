package com.joun.sosmall.bank;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BankController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final BankServiceImpl service;

  @GetMapping("/banks")
  public List<Bank> findBanks() throws Exception {
    return service.find();
  }

  @GetMapping("/banks/{code}")
  public Bank findBankByCode(@PathVariable String code) throws Exception {
    Optional<Bank> bank = service.findByCode(code);

    if (!bank.isPresent()) {
      throw new NotFoundException();
    }
    return bank.get();
  }

  @PostMapping("/banks")
  public void create(@RequestBody BankReqDto req) throws Exception {
    service.create(req);
  }
}
