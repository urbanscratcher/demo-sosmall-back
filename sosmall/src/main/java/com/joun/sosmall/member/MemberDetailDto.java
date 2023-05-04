package com.joun.sosmall.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joun.sosmall.address.AddressDetailDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MemberDetailDto extends MemberListDto {
  private AddressDetailDto address;

  public MemberDetailDto(Member member) {
    super(member);

    if (member.getAddress() != null) {
      this.address = new AddressDetailDto(member.getAddress());
    }
  }

}
