package com.joun.sosmall.address;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.member.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AddressRepository addressRepository;

  public void create(AddressCreateDto dto) throws Exception {
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
    address.setUpdate(dto);
    addressRepository.save(address);

  }

  public void delete(int addressId) throws Exception {
    Optional<Address> optionalAddress = addressRepository.findById(addressId);
    if (optionalAddress.isPresent() == false) {
      throw new NotFoundException("address not exist");
    }
    addressRepository.deleteById(addressId);
  }

}
