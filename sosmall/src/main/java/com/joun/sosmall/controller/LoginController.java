package com.joun.sosmall.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.common.exception.UnauthenticatedException;
import com.joun.sosmall.dtoRequest.LoginDto;
import com.joun.sosmall.dtoRequest.MemberCreateDto;
import com.joun.sosmall.dtoResponse.TokensDto;
import com.joun.sosmall.entity.Member;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.serviceImpl.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final UserJWTService userJwtService;
  private final MemberServiceImpl memberService;

  @PostMapping("/login")
  public TokensDto login(@RequestBody LoginDto req) throws Exception {

    Optional<Member> member = memberService.findByEmail(req.getEmail());
    if (!member.isPresent()) {
      throw new NotFoundException("member not exists");
    }

    // TODO crypto
    if (req.getPassword() == "" || !member.get().getPassword().equals(req.getPassword())) {
      System.out.println(member.get().getPassword());
      System.out.println(req.getPassword());
      throw new UnauthenticatedException("password not matched");
    }

    System.out.println(String.valueOf(member.get().getId()));

    Map<String, Object> memberMap = new HashMap<>();
    memberMap.put("id", String.valueOf(member.get().getId()));

    String access = userJwtService.createAccessToken(memberMap);
    String refresh = userJwtService.createRefreshToken(memberMap);

    return new TokensDto(access, refresh);
  }

  @PostMapping("/refresh")
  public Map<String, Object> refresh(@RequestBody LoginDto req) throws Exception {
    // Map<String, Object> memberMap = new HashMap<>();
    // memberMap.put("id", member.get().getId());

    // memberMap.put("id", member.get().getId());
    // String refresh = userJwtService.createRefreshToken(memberMap);

    Map<String, Object> refreshMap = new HashMap<>();
    refreshMap.put("refreshToken", "refreshed");
    return refreshMap;
  }

  @PostMapping("/signup")
  public void create(@RequestBody MemberCreateDto req) throws Exception {
    memberService.create(req);
  }

}
