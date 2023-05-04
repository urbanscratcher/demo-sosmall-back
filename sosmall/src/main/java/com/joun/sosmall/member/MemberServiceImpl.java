package com.joun.sosmall.member;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.DuplicatedException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.common.util.AES256Util;
import com.joun.sosmall.dtoRequest.PageSortDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final MemberRepository repo;
  private final MemberMapper mapper;

  public void initDB() throws Exception {
    List<Member> members = IntStream
        .rangeClosed(1, 100)
        .mapToObj(i -> {
          return Member.builder()
              .name("이름" + i)
              .email("이메일" + i)
              .password("password" + i)
              .phone("010111122" + i)
              .isEmailAllowed(false)
              .birthAt(new Date())
              .build();
        })
        .collect(Collectors.toList());

    repo.saveAll(members);
  }

  public Map<String, Object> find(Map<String, Object> query) {
    Map<String, Object> result = new LinkedHashMap<>();
    PageSortDto page = new PageSortDto(query);
    result.put("total", mapper.findsCount(query));
    result.put("list", mapper.finds(page, query));
    return result;
  }

  public void create(MemberReqDto dto) throws Exception {
    Optional<FindByEmailDto> member = repo.findByEmail(dto.getEmail());
    if (member.isPresent()) {
      throw new DuplicatedException("email should be unique");
    }

    String inputPassword = dto.getPassword();
    AES256Util AES256 = new AES256Util();
    String decryptedPassword = AES256.decrypt(inputPassword);
    dto.setPassword(BCrypt.hashpw(decryptedPassword, BCrypt.gensalt()));

    repo.save(dto.toEntity());
  }

  public void update(int id, MemberReqDto dto) throws Exception {
    Optional<Member> member = repo.findById(id);

    if (!member.isPresent()) {
      throw new NotFoundException();
    }

    member.get().setUpdate(dto);
    repo.save(member.get());
  }

  public Optional<Member> findById(int id, Boolean isEntityOnly) {
    if (!isEntityOnly) {
      return repo.findByIdWithAddresses(id);
    }
    return repo.findById(id);
  }

  public void delete(int id) throws Exception {
    if (!isExists(id)) {
      throw new NotFoundException();
    }
    repo.deleteById(id);
  }

  public Optional<FindByEmailDto> findByEmail(String email) {
    return repo.findByEmail(email);
  }

  public boolean isExists(int id) {
    return repo.existsById(id);
  }

}
