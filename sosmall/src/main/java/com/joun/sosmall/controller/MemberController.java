package com.joun.sosmall.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.common.exception.DataNotFoundException;
import com.joun.sosmall.common.exception.DuplicatedException;
import com.joun.sosmall.dtoRequest.AddressRequestDto;
import com.joun.sosmall.dtoRequest.BankAccountRequestDto;
import com.joun.sosmall.dtoRequest.MemberRequestDto;
import com.joun.sosmall.dtoResponse.BankAccountResponseDto;
import com.joun.sosmall.dtoResponse.MemberResponseDto;
import com.joun.sosmall.entity.BankAccount;
import com.joun.sosmall.entity.Member;
import com.joun.sosmall.service.AddressService;
import com.joun.sosmall.service.BankAccountService;
import com.joun.sosmall.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final MemberService memberService;
  private final AddressService addressService;
  private final BankAccountService bankAccountService;

  @PostMapping("/members")
  public void create(@RequestBody MemberRequestDto req) {
    memberService.create(req);
  }

  @GetMapping("/members")
  public List<MemberResponseDto> find() {
    return memberService.find();
  }

  @GetMapping("/members/{id}")
  public MemberResponseDto findById(@PathVariable(name = "id") int id) throws Exception {
    Optional<Member> member = memberService.findById(id);
    if (member.isPresent() == false) {
      throw new DataNotFoundException();
    }
    return new MemberResponseDto(member.get());
  }

  @PutMapping("/members/{id}")
  public void update(@PathVariable(name = "id") int id, @RequestBody MemberRequestDto req)
      throws Exception {
    memberService.update(id, req);
  }

  @DeleteMapping("/members/{id}")
  public void delete(@PathVariable int id) throws Exception {
    memberService.delete(id);
  }

  @PostMapping("/members/{id}/addresses")
  public void addAddress(@PathVariable int id, @RequestBody AddressRequestDto req)
      throws Exception {
    Optional<Member> member = memberService.findById(id);
    if (member.isPresent() == false) {
      throw new DataNotFoundException();
    }
    req.setMember(member.get());
    addressService.create(req);
  }

  @PutMapping("/members/{id}/addresses/{addressId}")
  public void updateAddress(@PathVariable int id, @PathVariable int addressId, @RequestBody AddressRequestDto req)
      throws Exception {
    addressService.update(addressId, req);
  }

  @DeleteMapping("/members/{id}/addresses/{addressId}")
  public void deleteAddress(@PathVariable int id, @PathVariable int addressId) throws Exception {
    Optional<Member> member = memberService.findById(id);
    if (member.isPresent() == false) {
      throw new DataNotFoundException();
    }
    addressService.delete(addressId);
  }

  @PostMapping("/members/{id}/bankAccounts")
  public void createBankAccount(@PathVariable int id, @RequestBody BankAccountRequestDto req)
      throws Exception {

    Optional<Member> member = memberService.findById(id);
    if (!member.isPresent()) {
      throw new DataNotFoundException("member not exist");
    }

    Optional<BankAccount> bankAccount = bankAccountService.findByMemberId(id);
    if (bankAccount.isPresent()) {
      throw new DuplicatedException();
    }

    req.setMember(member.get());
    bankAccountService.create(req);
  }

  @GetMapping("/members/{id}/bankAccount")
  public BankAccountResponseDto findByMemberId(@PathVariable(name = "id") int memberId) throws Exception {
    Optional<Member> member = memberService.findById(memberId);
    if (!member.isPresent()) {
      throw new DataNotFoundException("member not exist");
    }

    Optional<BankAccount> bankAccount = bankAccountService.findByMemberId(member.get().getId());

    if (!bankAccount.isPresent()) {
      throw new DataNotFoundException("bank account not exist");
    }

    return new BankAccountResponseDto(bankAccount.get());
  }

  @PutMapping("/members/{id}/bankAccount")
  public void updateBankAccount(@PathVariable(name = "id") int memberId, @RequestBody BankAccountRequestDto req)
      throws Exception {
    bankAccountService.update(memberId, req);
  }

  @DeleteMapping("/members/{id}/bankAccount")
  public void deleteBankAccount(@PathVariable(name = "id") int memberId) throws Exception {
    bankAccountService.delete(memberId);
  }

}
