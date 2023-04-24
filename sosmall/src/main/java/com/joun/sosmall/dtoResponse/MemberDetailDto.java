package com.joun.sosmall.dtoResponse;

import java.util.List;
import java.util.stream.Collectors;

import com.joun.sosmall.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDetailDto extends MemberListDto {
  private List<AddressDetailDto> addresses;

  public MemberDetailDto(Member member) {
    super(member);
    this.addresses = member.getAddresses().stream().map((o) -> new AddressDetailDto(o)).collect(Collectors.toList());
  }

}
