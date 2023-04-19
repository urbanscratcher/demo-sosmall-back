package com.joun.sosmall.service;

import java.util.List;
import java.util.Optional;

import com.joun.sosmall.dtoRequest.MemberRequestDto;
import com.joun.sosmall.dtoResponse.MemberResponseDto;
import com.joun.sosmall.entity.Member;

public interface MemberService {
  public void create(MemberRequestDto dto);

  public List<MemberResponseDto> find();

  public Optional<Member> findById(int id) throws Exception;

  public void update(int id, MemberRequestDto dto) throws Exception;

  public void delete(int id) throws Exception;
}
