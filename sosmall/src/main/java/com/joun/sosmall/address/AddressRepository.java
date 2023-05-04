package com.joun.sosmall.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.member.Member;

public interface AddressRepository extends JpaRepository<Address, Integer> {

  public Optional<Address> findByIdAndMember(int id, Member member);

}
