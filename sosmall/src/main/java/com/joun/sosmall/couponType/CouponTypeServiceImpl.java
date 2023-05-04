package com.joun.sosmall.couponType;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponTypeServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CouponTypeRepository repo;

  public List<CouponType> find() {
    return repo.findAll();
  }

  public Optional<CouponType> findById(int id) {
    return repo.findById(id);
  }

  public void create(CouponTypeReqDto dto) {
    repo.save(dto.ToEntity());
  }

}
