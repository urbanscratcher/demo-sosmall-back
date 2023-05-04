package com.joun.sosmall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.dtoRequest.ReviewCreateDto;
import com.joun.sosmall.dtoResponse.ReviewDetailDto;
import com.joun.sosmall.dtoResponse.ReviewListDto;
import com.joun.sosmall.entity.Product;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.member.Member;
import com.joun.sosmall.serviceImpl.ReviewServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ReviewServiceImpl service;
  private final UserJWTService userJWTService;

  @PostMapping("/reviews")
  public void create(@RequestBody ReviewCreateDto req, HttpServletRequest request) throws Exception {
    int memberId = userJWTService.getId(request);
    req.setMember(Member.builder().id(memberId).build());
    req.setProduct(Product.builder().id(req.getProductId()).build());

    service.create(req);
  }

  @PutMapping("/reviews/{id}")
  public void update(@PathVariable int id, @RequestBody ReviewCreateDto req) throws Exception {
    service.update(id, req);
  }

  @DeleteMapping("/reviews/{id}")
  public void delete(@PathVariable int id) throws Exception {
    service.delete(id);
  }

  @GetMapping("/public/reviews/{id}")
  public ReviewDetailDto findById(@PathVariable int id) throws Exception {
    return service.findById(id);
  }

  @GetMapping("/public/reviews")
  public List<ReviewListDto> find() throws Exception {
    return service.find();
  }

}
