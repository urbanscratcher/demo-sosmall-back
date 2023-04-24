package com.joun.sosmall.dtoResponse;

import java.util.Date;

import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberListDto {
  private int id;
  private String name;
  private String email;
  private Boolean isEmailAllowed;
  private String phone;
  private Date birthAt;
  private Date regAt;
  private Date modAt;

  public MemberListDto(Member member) {
    this.id = member.getId();
    this.name = member.getName();
    this.email = member.getEmail();
    this.isEmailAllowed = member.getIsEmailAllowed();
    this.phone = member.getPhone();
    this.birthAt = member.getBirthAt();
    this.regAt = member.getRegAt();
    this.modAt = member.getModAt();
  }

}
