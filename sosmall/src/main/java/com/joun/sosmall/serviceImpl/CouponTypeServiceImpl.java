package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.entity.CouponType;
import com.joun.sosmall.repository.CouponTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponTypeServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CouponTypeRepository couponTypeRepository;

  public List<CouponType> find() {
    return couponTypeRepository.findAll();
  }

  public Optional<CouponType> findById(int id) {
    return couponTypeRepository.findById(id);
  }

}
