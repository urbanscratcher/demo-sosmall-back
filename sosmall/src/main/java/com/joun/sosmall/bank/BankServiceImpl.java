package com.joun.sosmall.bank;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.DuplicatedException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final BankRepository repo;

  public void create(BankReqDto dto) throws Exception {
    Optional<Bank> bank = repo.findByCode(dto.getCode());
    if (bank.isPresent()) {
      throw new DuplicatedException("code already exists");
    }
    repo.save(dto.toEntity());
  }

  public List<Bank> find() {
    return repo.findAll();
  }

  public Optional<Bank> findByCode(String code) {
    return repo.findByCode(code);
  }
}
