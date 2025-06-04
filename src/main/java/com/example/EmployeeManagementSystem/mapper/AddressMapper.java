package com.example.EmployeeManagementSystem.mapper;

import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddressMapper {
    public AddressDTO entityToDto(Address address) {

        log.debug("entityToDto- Converting Entity to DTO");

        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setAddress(address.getAddress());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPincode(address.getPincode());
        return dto;
    }

    public Address dtoToEntity(AddressDTO dto) {

        log.debug("dtoToentity- Converting DTO To Entity");

        Address address = new Address();
        address.setId(dto.getId());
        address.setAddress(dto.getAddress());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPincode());
        return address;
    }
}