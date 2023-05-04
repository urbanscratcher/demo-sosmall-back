package com.joun.sosmall.controller;

import java.util.List;

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

import com.joun.sosmall.dtoRequest.InterestCreateDto;
import com.joun.sosmall.dtoResponse.ProductBasicDto;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.serviceImpl.InterestServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InterestController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final InterestServiceImpl service;
  private final UserJWTService userJWTService;

  @PostMapping("/interests")
  public void create(@RequestBody InterestCreateDto req, HttpServletRequest request) throws Exception {
    int memberId = userJWTService.getId(request);
    req.setMemberId(memberId);
    service.create(req);
  }

  @GetMapping("/interests/products")
  public List<ProductBasicDto> findByMemberId(HttpServletRequest request) throws Exception {
    int memberId = userJWTService.getId(request);
    return service.findByMemberId(memberId);
  }

  @GetMapping("/interests/products/{productId}")
  public Long findByProductId(@PathVariable int productId) throws Exception {
    return service.findByProductId(productId);
  }

  @DeleteMapping("/interests/products/{productId}")
  public void delete(@PathVariable int productId, HttpServletRequest request) throws Exception {
    int memberId = userJWTService.getId(request);

    InterestCreateDto dto = new InterestCreateDto();
    dto.setMemberId(memberId);
    dto.setProductId(productId);

    service.delete(dto);
  }

}
