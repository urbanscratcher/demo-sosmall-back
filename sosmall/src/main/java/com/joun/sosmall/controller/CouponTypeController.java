package com.joun.sosmall.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.entity.CouponType;
import com.joun.sosmall.serviceImpl.CouponTypeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponTypeController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CouponTypeServiceImpl service;

  @GetMapping("/coupon-types")
  public List<CouponType> findBanks() {
    return service.find();
  }

  @GetMapping("/coupon-types/{id}")
  public CouponType findBankByCode(@PathVariable int id) throws Exception {
    Optional<CouponType> couponType = service.findById(id);

    if (!couponType.isPresent()) {
      throw new NotFoundException();
    }
    return couponType.get();
  }

}
