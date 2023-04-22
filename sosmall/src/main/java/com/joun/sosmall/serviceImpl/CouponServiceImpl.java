package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.dtoRequest.CouponCreateDto;
import com.joun.sosmall.dtoResponse.CouponListDto;
import com.joun.sosmall.entity.Coupon;
import com.joun.sosmall.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CouponRepository repo;

  public void create(CouponCreateDto dto) throws Exception {
    repo.save(dto.toEntity());
  }

  public List<CouponListDto> find() {
    return repo.findAll().stream().map((o) -> new CouponListDto(o)).collect(Collectors.toList());
  }

  public Optional<Coupon> findById(int id) throws Exception {
    return repo.findById(id);
  }

  public void delete(int id) throws Exception {
    repo.deleteById(id);
  }
}
