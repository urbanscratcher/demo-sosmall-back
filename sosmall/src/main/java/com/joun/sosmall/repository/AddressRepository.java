package com.joun.sosmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
  public List<Address> findByMemberIdAndIsMain(int memberId, Boolean isMain);

}
