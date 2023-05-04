package com.joun.sosmall.coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

  @Query("SELECT distinct c from Coupon c left join fetch c.couponType order by c.id DESC")
  public List<Coupon> findAllCoupons();

}
