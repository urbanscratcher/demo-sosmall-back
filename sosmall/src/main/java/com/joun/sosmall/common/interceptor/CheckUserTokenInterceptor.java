package com.joun.sosmall.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.joun.sosmall.common.exception.InvalidRequestException;
import com.joun.sosmall.common.exception.UnauthenticatedException;
import com.joun.sosmall.jwt.UserJWTService;
import com.joun.sosmall.member.MemberServiceImpl;

@Component
public class CheckUserTokenInterceptor implements HandlerInterceptor {

  @Autowired
  UserJWTService userJWTService;

  @Autowired
  MemberServiceImpl memberService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String userToken = request.getHeader("Authorization");

    if (userToken == null || userToken.isEmpty())
      throw new InvalidRequestException("User Token is not exist.");

    String accessToken = userToken.substring(7);

    if (!userJWTService.checkValidateAccessToken(accessToken)) {
      throw new UnauthenticatedException("User Token is not valid");
    }

    int id = userJWTService.getId(request);

    if (!memberService.isExists(id)) {
      throw new InvalidRequestException("User not exist");
    }

    return HandlerInterceptor.super.preHandle(request, response, handler);
  }
}
