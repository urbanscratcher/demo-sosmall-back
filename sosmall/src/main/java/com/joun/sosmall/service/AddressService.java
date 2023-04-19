package com.joun.sosmall.service;

import java.util.List;
import java.util.Optional;

import com.joun.sosmall.dtoRequest.AddressRequestDto;
import com.joun.sosmall.dtoResponse.AddressResponseDto;
import com.joun.sosmall.entity.Address;

public interface AddressService {

  public void create(AddressRequestDto dto);

  public List<AddressResponseDto> find();

  public Optional<Address> findById(int id) throws Exception;

  public void update(int addressId, AddressRequestDto dto) throws Exception;

  public void delete(int addressId) throws Exception;

}
