package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.DataNotFoundException;
import com.joun.sosmall.common.exception.DuplicatedException;
import com.joun.sosmall.common.exception.InvalidRelatedDataException;
import com.joun.sosmall.dtoRequest.BankAccountRequestDto;
import com.joun.sosmall.dtoResponse.BankAccountResponseDto;
import com.joun.sosmall.entity.Bank;
import com.joun.sosmall.entity.BankAccount;
import com.joun.sosmall.repository.BankAccountRepository;
import com.joun.sosmall.repository.BankRepository;
import com.joun.sosmall.service.BankAccountService;
import com.joun.sosmall.service.BankService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final BankAccountRepository bankAccountRepository;
  private final BankRepository bankRepository;
  private final BankService bankService;

  @Override
  public void create(BankAccountRequestDto dto) throws Exception {
    Optional<Bank> bank = bankService.findByCode(dto.getBankCode());
    if (!bank.isPresent()) {
      throw new InvalidRelatedDataException("bank code is invalid");
    }
    dto.setBank(bank.get());
    bankAccountRepository.save(dto.ToEntity());

  }

  @Override
  public List<BankAccountResponseDto> find() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  @Override
  public Optional<BankAccount> findById(int id) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void update(int memberId, BankAccountRequestDto dto) throws Exception {
    Optional<BankAccount> bankAccount = bankAccountRepository.findByMemberId(memberId);

    if (!bankAccount.isPresent()) {
      throw new DataNotFoundException();
    }

    Optional<Bank> bank = bankService.findByCode(dto.getBankCode());
    if (!bank.isPresent()) {
      throw new InvalidRelatedDataException("bank code is invalid");
    }
    dto.setBank(bank.get());

    bankAccount.get().setUpdate(dto);
    bankAccountRepository.save(bankAccount.get());
  }

  @Override
  public void delete(int memberId) throws Exception {
    Optional<BankAccount> bankAccount = bankAccountRepository.findByMemberId(memberId);
    if (!bankAccount.isPresent()) {
      throw new DataNotFoundException();
    }
    bankAccountRepository.deleteById(bankAccount.get().getId());
  }

  @Override
  public Optional<BankAccount> findByMemberId(int memberId) throws Exception {
    return bankAccountRepository.findByMemberId(memberId);
  }

}
