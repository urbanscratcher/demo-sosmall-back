package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.DuplicatedException;
import com.joun.sosmall.common.exception.InvalidRequestException;
import com.joun.sosmall.common.exception.LogicalConflictException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.AddressCreateDto;
import com.joun.sosmall.entity.Address;
import com.joun.sosmall.entity.Member;
import com.joun.sosmall.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AddressRepository addressRepository;

  public void create(AddressCreateDto dto) throws Exception {
    List<Address> mainAddresses = addressRepository.findByMemberIdAndIsMain(dto.getMember().getId(), true);

    if (dto.getIsMain() && (!mainAddresses.isEmpty() || mainAddresses.size() != 0)) {
      throw new DuplicatedException("main address already exists");
    }

    if (mainAddresses.isEmpty() || mainAddresses.size() == 0) {
      dto.setIsMain(true);
    }

    addressRepository.save(dto.toEntity());
  }

  public Optional<Address> findById(int id) {
    return addressRepository.findById(id);
  }

  public void update(int addressId, AddressCreateDto dto) throws Exception {
    int memberId = 1;
    Optional<Address> optionalAddress = addressRepository.findByIdAndMember(addressId,
        Member.builder().id(memberId).build());

    if (!optionalAddress.isPresent()) {
      throw new NotFoundException();
    }

    Address address = optionalAddress.get();

    List<Address> mainAddresses = addressRepository.findByMemberIdAndIsMain(address.getMember().getId(),
        true);

    if (mainAddresses.isEmpty() || mainAddresses.size() == 0) {
      throw new InvalidRequestException("main address is required");
    }

    address.setUpdate(dto);

    if (dto.getIsMain() && (!mainAddresses.isEmpty() || mainAddresses.size() != 0)
        && mainAddresses.get(0).getId() != address.getId()) {
      throw new DuplicatedException("main address already exists");
    }

    if ((!mainAddresses.isEmpty() || mainAddresses.size() != 0)
        && mainAddresses.get(0).getId() == address.getId() && !address.getIsMain()) {
      throw new InvalidRequestException("main address is required");
    }

    addressRepository.save(address);

  }

  public void delete(int addressId) throws Exception {
    Optional<Address> optionalAddress = addressRepository.findById(addressId);
    if (optionalAddress.isPresent() == false) {
      throw new NotFoundException("address not exist");
    }

    if (optionalAddress.get().getIsMain()) {
      throw new LogicalConflictException("main address should not be deleted");
    }
    addressRepository.deleteById(addressId);
  }

}
