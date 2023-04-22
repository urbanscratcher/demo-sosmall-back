package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.InvalidRequestException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.BankAccountCreateDto;
import com.joun.sosmall.dtoResponse.BankAccountResponseDto;
import com.joun.sosmall.entity.Bank;
import com.joun.sosmall.entity.BankAccount;
import com.joun.sosmall.repository.BankAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final BankAccountRepository repo;
  private final BankServiceImpl bankService;

  public void create(BankAccountCreateDto dto) throws Exception {
    Optional<Bank> bank = bankService.findByCode(dto.getBankCode());

    if (!bank.isPresent()) {
      throw new InvalidRequestException("bank code is invalid");
    }

    dto.setBank(bank.get());
    repo.save(dto.toEntity());
  }

  public List<BankAccountResponseDto> find() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  public Optional<BankAccount> findById(int id) throws Exception {
    return repo.findById(id);
  }

  public void update(int id, BankAccountCreateDto dto) throws Exception {
    Optional<BankAccount> bankAccount = repo.findById(id);

    if (!bankAccount.isPresent()) {
      throw new NotFoundException();
    }

    if (dto.getBankCode() != null) {
      Optional<Bank> bank = bankService.findByCode(dto.getBankCode());
      if (!bank.isPresent()) {
        throw new InvalidRequestException("bank code is invalid");
      }
      dto.setBank(bank.get());
    }

    bankAccount.get().setUpdate(dto);
    repo.save(bankAccount.get());
  }

  public void delete(int id) throws Exception {
    Optional<BankAccount> bankAccount = repo.findById(id);

    if (!bankAccount.isPresent()) {
      throw new NotFoundException();
    }

    repo.deleteById(id);
  }

  public Optional<BankAccount> findByMemberId(int memberId) throws Exception {
    return repo.findByMemberId(memberId);
  }

}
