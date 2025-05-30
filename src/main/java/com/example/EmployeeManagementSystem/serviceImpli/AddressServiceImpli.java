package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.AddressRepository;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourseNotFoundException;
import com.example.EmployeeManagementSystem.mapper.AddressMapper;
import com.example.EmployeeManagementSystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpli implements AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressDTO createAddress(AddressDTO dto) {
        Address address = addressMapper.dtoToEntity(dto);
        return addressMapper.entityToDto(addressRepo.save(address));
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        return addressMapper.entityToDto(
                addressRepo.findById(id).orElseThrow(() -> new RuntimeException("Address not found"))
        );
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressRepo.findAll().stream().map(addressMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO dto) {
        Address address = addressRepo.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));

        //pass all field data otherwise store Null value
        address.setAddress(dto.getAddress());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPincode());

        return addressMapper.entityToDto(addressRepo.save(address));
    }

    @Override
    public String deleteAddress(Long id) {
        addressRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Address not Found ID: "+id));

        addressRepo.deleteById(id);
        return "Address Delete Successfully ID:"+id;
    }
}