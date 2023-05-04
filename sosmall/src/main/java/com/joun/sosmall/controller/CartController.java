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

import com.joun.sosmall.dtoRequest.CartCreateDto;
import com.joun.sosmall.dtoResponse.CartListDto;
import com.joun.sosmall.entity.Stock;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.serviceImpl.CartServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CartServiceImpl service;
  private final UserJWTService userJWTService;

  @PostMapping("/carts")
  public void create(@RequestBody CartCreateDto req, HttpServletRequest request) throws Exception {
    int id = userJWTService.getId(request);
    req.setMemberId(id);
    service.create(req);
  }

  @DeleteMapping("/carts/stocks/{stockId}")
  public void remove(@PathVariable int stockId, HttpServletRequest request) throws Exception {
    int memberId = userJWTService.getId(request);

    CartCreateDto dto = new CartCreateDto();
    dto.setMemberId(memberId);
    dto.setStock(Stock.builder().id(stockId).build());

    service.remove(dto);
  }

  @DeleteMapping("/carts")
  public void removeAll(HttpServletRequest request) throws Exception {
    int memberId = userJWTService.getId(request);
    service.removeAll(memberId);
  }

  @GetMapping("/carts")
  public List<CartListDto> findByMemberId(HttpServletRequest request) throws Exception {
    int memberId = userJWTService.getId(request);
    return service.findByMemberId(memberId);
  }

}
