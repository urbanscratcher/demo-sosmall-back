package com.joun.sosmall.controller;

import java.util.List;
import java.util.Optional;

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

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.AddressCreateDto;
import com.joun.sosmall.dtoResponse.AddressDetailDto;
import com.joun.sosmall.entity.Address;
import com.joun.sosmall.entity.Member;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.serviceImpl.AddressServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AddressController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AddressServiceImpl service;
  private final UserJWTService userJWTService;

  @PostMapping("/addresses")
  public void create(@RequestBody AddressCreateDto req, HttpServletRequest request) throws Exception {
    int id = userJWTService.getId(request);
    req.setMember(Member.builder().id(id).build());
    service.create(req);
  }

  @PutMapping("/addresses/{id}")
  public void update(@PathVariable(name = "id") int id, @RequestBody AddressCreateDto req)
      throws Exception {
    service.update(id, req);
  }

  @GetMapping("/addresses/{id}")
  public AddressDetailDto findById(@PathVariable(name = "id") int id) throws Exception {
    Optional<Address> address = service.findById(id);
    if (address.isPresent() == false) {
      throw new NotFoundException();
    }
    return new AddressDetailDto(address.get());
  }

  @DeleteMapping("/addresses/{id}")
  public void delete(@PathVariable int id) throws Exception {
    service.delete(id);
  }

}
