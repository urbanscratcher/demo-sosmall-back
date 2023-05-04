package com.joun.sosmall.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.common.exception.UnauthenticatedException;
import com.joun.sosmall.common.util.AES256Util;
import com.joun.sosmall.dtoResponse.TokensDto;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.member.FindByEmailDto;
import com.joun.sosmall.member.MemberReqDto;
import com.joun.sosmall.member.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final UserJWTService userJwtService;
  private final MemberServiceImpl memberService;

  @PostMapping("/public/login")
  public TokensDto login(@RequestBody LoginDto req) throws Exception {

    Optional<FindByEmailDto> member = memberService.findByEmail(req.getEmail());

    if (!member.isPresent()) {
      throw new NotFoundException("member not exists");
    }

    // password check
    String existingPassword = member.get().getPassword();
    String inputPassword = req.getPassword();
    AES256Util AES256 = new AES256Util();
    String decryptedPassword = AES256.decrypt(inputPassword);
    if (inputPassword == "" || inputPassword == null) {
      throw new UnauthenticatedException("no password");
    }
    if (!BCrypt.checkpw(decryptedPassword, existingPassword)) {
      throw new UnauthenticatedException("password not matched");
    }

    Map<String, Object> payload = new HashMap<>();
    payload.put("id", String.valueOf(member.get().getId()));

    String access = userJwtService.createAccessToken(payload);
    String refresh = userJwtService.createRefreshToken(payload);

    return new TokensDto(access, refresh);
  }

  @PostMapping("/refresh")
  public Map<String, Object> refresh(@RequestBody FindByEmailDto req) throws Exception {
    // String refresh = userJwtService.createRefreshToken(memberMap);

    Map<String, Object> refreshMap = new HashMap<>();
    refreshMap.put("refreshToken", "refreshed");
    return refreshMap;
  }

  @PostMapping("/public/signup")
  public void create(@RequestBody MemberReqDto req) throws Exception {
    memberService.create(req);
  }

}
