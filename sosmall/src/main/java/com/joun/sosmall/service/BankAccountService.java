package com.joun.sosmall.service;

import java.util.List;
import java.util.Optional;

import com.joun.sosmall.dtoRequest.BankAccountRequestDto;
import com.joun.sosmall.dtoResponse.BankAccountResponseDto;
import com.joun.sosmall.entity.BankAccount;

public interface BankAccountService {

  public void create(BankAccountRequestDto dto) throws Exception;

  public List<BankAccountResponseDto> find();

  public Optional<BankAccount> findById(int id) throws Exception;

  public void update(int memberId, BankAccountRequestDto dto) throws Exception;

  public void delete(int addressId) throws Exception;

  public Optional<BankAccount> findByMemberId(int memberId) throws Exception;
}
