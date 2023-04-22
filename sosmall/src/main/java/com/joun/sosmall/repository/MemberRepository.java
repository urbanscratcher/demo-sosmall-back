package com.joun.sosmall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.joun.sosmall.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

  @Query("SELECT distinct m from Member m left join fetch m.addresses")
  public List<Member> findAllWithAddresses();

  @Query("SELECT distinct m from Member m left join fetch m.addresses a where m.id = :id")
  public Optional<Member> findByIdWithAddresses(@Param("id") int id);

  public Optional<Member> findByEmail(@Param("email") String email);

}
