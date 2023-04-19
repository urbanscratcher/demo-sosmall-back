package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.DataNotFoundException;
import com.joun.sosmall.dtoRequest.AddressRequestDto;
import com.joun.sosmall.dtoResponse.AddressResponseDto;
import com.joun.sosmall.entity.Address;
import com.joun.sosmall.repository.AddressRepository;
import com.joun.sosmall.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AddressRepository addressRepository;

  @Override
  public void create(AddressRequestDto dto) {
    addressRepository.save(dto.ToEntity());
  }

  @Override
  public List<AddressResponseDto> find() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'find'");
  }

  @Override
  public Optional<Address> findById(int id) {
    return addressRepository.findById(id);
  }

  @Override
  public void update(int addressId, AddressRequestDto dto) throws Exception {
    Optional<Address> optionalAddress = addressRepository.findById(addressId);
    if (optionalAddress.isPresent() == false) {
      throw new DataNotFoundException();
    }
    Address address = optionalAddress.get();
    address.setUpdate(dto);
    addressRepository.save(address);
  }

  @Override
  public void delete(int addressId) throws Exception {
    Optional<Address> optionalAddress = addressRepository.findById(addressId);
    if (optionalAddress.isPresent() == false) {
      throw new DataNotFoundException();
    }
    addressRepository.deleteById(addressId);
  }

}
