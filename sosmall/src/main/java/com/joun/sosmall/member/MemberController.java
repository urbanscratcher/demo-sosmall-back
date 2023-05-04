package com.joun.sosmall.member;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.jwt.UserJWTService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final MemberServiceImpl service;
  private final UserJWTService userJWTService;

  @PutMapping("/{id}")
  public void update(@PathVariable(name = "id") int id, @RequestBody MemberReqDto req, HttpServletRequest request)
      throws Exception {
    service.update(id, req);
  }

  @GetMapping()
  public Map<String, Object> find(@RequestParam() Map<String, Object> query) throws Exception {
    logger.info(query.toString());
    return service.find(query);
  }

  @GetMapping("/{id}")
  public MemberDetailDto findById(@PathVariable(name = "id") int id) throws Exception {
    Optional<Member> member = service.findById(id, false);
    if (!member.isPresent()) {
      throw new NotFoundException();
    }
    return new MemberDetailDto(member.get());
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) throws Exception {
    service.delete(id);
  }

}
