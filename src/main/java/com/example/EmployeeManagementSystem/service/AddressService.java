package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    AddressDTO createAddress(AddressDTO dto);
    AddressDTO updateAddress(Long id, AddressDTO dto);
    AddressDTO getAddressById(Long id);
    List<AddressDTO> getAllAddresses();
    void deleteAddress(Long id);
}
