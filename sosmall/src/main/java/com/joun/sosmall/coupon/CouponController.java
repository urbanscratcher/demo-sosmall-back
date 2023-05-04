package com.joun.sosmall.coupon;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.InvalidRequestException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.couponType.CouponType;
import com.joun.sosmall.couponType.CouponTypeServiceImpl;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.member.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CouponServiceImpl service;
  private final CouponTypeServiceImpl couponTypeService;
  private final UserJWTService userJWTService;

  @PostMapping("/coupons")
  public void create(@RequestBody CouponCreateDto req, HttpServletRequest request) throws Exception {
    int id = userJWTService.getId(request);
    req.setMember(Member.builder().id(id).build());

    logger.info(req.toString());
    System.out.println("________________");

    Optional<CouponType> couponType = couponTypeService.findById(req.getCouponTypeId());

    if (!couponType.isPresent()) {
      throw new InvalidRequestException("coupon type not exists");
    }

    req.setCouponType(couponType.get());

    service.create(req);
  }

  @GetMapping("/coupons")
  public List<CouponListDto> find() throws Exception {
    return service.find();
  }

  @GetMapping("/coupons/{id}")
  public CouponListDto findById(@PathVariable(name = "id") int id) throws Exception {
    Optional<Coupon> coupon = service.findById(id);

    if (!coupon.isPresent()) {
      throw new NotFoundException();
    }

    return new CouponListDto(coupon.get());
  }

  @DeleteMapping("/coupons/{id}")
  public void delete(@PathVariable int id) throws Exception {
    Optional<Coupon> coupon = service.findById(id);
    if (coupon.isPresent() == false) {
      throw new NotFoundException();
    }
    service.delete(id);
  }

}
