package com.joun.sosmall.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.BankAccountCreateDto;
import com.joun.sosmall.dtoResponse.BankAccountResponseDto;
import com.joun.sosmall.entity.BankAccount;
import com.joun.sosmall.entity.Member;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.serviceImpl.BankAccountServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final BankAccountServiceImpl service;
  private final UserJWTService userJWTService;

  @PostMapping()
  public void create(@RequestBody BankAccountCreateDto req, HttpServletRequest request) throws Exception {
    int id = userJWTService.getId(request);
    req.setMember(Member.builder().id(id).build());

    System.out.println(req.toString());

    service.create(req);
  }

  @GetMapping("/{id}")
  public BankAccountResponseDto findById(@PathVariable(name = "id") int id) throws Exception {
    Optional<BankAccount> bankAccount = service.findById(id);
    if (!bankAccount.isPresent()) {
      throw new NotFoundException("bank account not exists");
    }
    return new BankAccountResponseDto(bankAccount.get());
  }

  @PutMapping("/{id}")
  public void update(@PathVariable(name = "id") int id, @RequestBody BankAccountCreateDto req) throws Exception {
    service.update(id, req);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable(name = "id") int id) throws Exception {
    service.delete(id);
  }

}
