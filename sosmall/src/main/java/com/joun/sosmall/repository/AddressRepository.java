package com.joun.sosmall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.entity.Address;
import com.joun.sosmall.entity.Member;

public interface AddressRepository extends JpaRepository<Address, Integer> {
  public List<Address> findByMemberIdAndIsMain(int memberId, Boolean isMain);

  public Optional<Address> findByIdAndMember(int id, Member member);

}
