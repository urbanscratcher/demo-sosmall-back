package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.DuplicatedException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.common.util.CryptoUtil;
import com.joun.sosmall.dtoRequest.MemberCreateDto;
import com.joun.sosmall.dtoResponse.MemberListDto;
import com.joun.sosmall.entity.Member;
import com.joun.sosmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final MemberRepository repo;

  public void create(MemberCreateDto dto) throws Exception {
    if (dto.getAddress() != null) {
      dto.getAddress().setIsMain(true);
    }

    String convertedPassword = CryptoUtil.decrypt(CryptoUtil.decrypt(dto.getPassword()));
    dto.setPassword(convertedPassword);

    try {
      repo.save(dto.toEntity());
    } catch (Exception e) {
      if (e instanceof ConstraintViolationException || e instanceof DataIntegrityViolationException) {
        throw new DuplicatedException("email should be unique");
      }

    }
  }

  public void update(int id, MemberCreateDto dto) throws Exception {
    Optional<Member> member = repo.findById(id);

    if (member.isPresent() == false) {
      throw new NotFoundException();
    }

    member.get().setUpdate(dto);
    repo.save(member.get());
  }

  public List<MemberListDto> find() {
    List<Member> members = repo.findAll();
    return members.stream().map((o) -> new MemberListDto(o)).collect(Collectors.toList());
  }

  public Optional<Member> findById(int id, Boolean isEntityOnly) {
    if (!isEntityOnly) {
      return repo.findByIdWithAddresses(id);
    }
    return repo.findById(id);
  }

  public void delete(int id) throws Exception {
    Optional<Member> member = repo.findById(id);

    if (!member.isPresent()) {
      throw new NotFoundException();
    }

    repo.deleteById(member.get().getId());
  }

  public Optional<Member> findByEmail(String email) {
    return repo.findByEmail(email);
  }

}
