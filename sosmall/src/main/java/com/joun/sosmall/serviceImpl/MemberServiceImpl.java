package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.DataNotFoundException;
import com.joun.sosmall.dtoRequest.AddressRequestDto;
import com.joun.sosmall.dtoRequest.MemberRequestDto;
import com.joun.sosmall.dtoResponse.MemberResponseDto;
import com.joun.sosmall.entity.Member;
import com.joun.sosmall.repository.AddressRepository;
import com.joun.sosmall.repository.MemberRepository;
import com.joun.sosmall.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final MemberRepository memberRepository;
  private final AddressRepository addressRepository;

  @Transactional
  @Override
  public void create(MemberRequestDto dto) {
    Member member = memberRepository.save(dto.ToEntity());
    if (dto.getAddress() != null) {
      AddressRequestDto addressDto = dto.getAddress();
      addressDto.setMember(member);
      addressRepository.save(addressDto.ToEntity());
    }
  }

  @Override
  public List<MemberResponseDto> find() {
    List<Member> members = memberRepository.findAll();
    return members.stream().map(o -> new MemberResponseDto(o)).collect(Collectors.toList());
  }

  @Override
  public Optional<Member> findById(int id) {
    return memberRepository.findByIdWithAddresses(id);
  }

  @Override
  public void update(int id, MemberRequestDto dto) throws Exception {
    Optional<Member> member = memberRepository.findById(id);

    if (member.isPresent() == false) {
      throw new DataNotFoundException();
    }

    member.get().setUpdate(dto);
    memberRepository.save(member.get());
  }

  @Override
  @Transactional
  public void delete(int id) throws Exception {
    Optional<Member> member = memberRepository.findById(id);

    if (member.isPresent() == false) {
      throw new DataNotFoundException();
    }

    member.get().getAddresses().stream().forEach(o -> addressRepository.deleteById(o.getId()));
    memberRepository.deleteById(member.get().getId());

  }

}
