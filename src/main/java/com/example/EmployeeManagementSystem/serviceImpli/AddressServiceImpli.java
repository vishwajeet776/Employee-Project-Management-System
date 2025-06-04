package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.AddressRepository;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourseNotFoundException;
import com.example.EmployeeManagementSystem.mapper.AddressMapper;
import com.example.EmployeeManagementSystem.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImpli implements AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressDTO createAddress(AddressDTO dto) {

        log.info("createAddress - Mapping DTO to Entity: {}", dto);
        Address address = addressMapper.dtoToEntity(dto);

        log.info("createAddress - Address saved with ID: {}", dto.getId());
        return addressMapper.entityToDto(addressRepo.save(address));
    }

    @Override
    public AddressDTO getAddressById(Long id) {

        log.info("getAddressById- finding Address BY ID:{} ", id);
        return addressMapper.entityToDto(
                addressRepo.findById(id).orElseThrow(() -> {
                    log.error("Address ID Not Found {} ", id);
              return  new RuntimeException("Address not found");
    }) );
    }

    @Override
    public List<AddressDTO> getAllAddresses() {

        log.info("getAllAddresses() - Fetching All Address ");
        return addressRepo.findAll().stream().map(addressMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO dto) {
        log.info("updateAddress - Finding ID{} ", id);
        Address address = addressRepo.findById(id).orElseThrow(() -> {

            log.error("ID Not Found {}", id);
            return new RuntimeException("Address not found");
        });

        //pass all field data otherwise store Null value
        address.setAddress(dto.getAddress());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPincode());

        return addressMapper.entityToDto(addressRepo.save(address));
    }

    @Override
    public String deleteAddress(Long id) {

        log.info("deleteAddress - Finding ID in Database {}",id);
        addressRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Address not Found ID: "+id));

        log.info("deleting ID:{}" , id);
        addressRepo.deleteById(id);
        return "Address Delete Successfully ID:"+id;
    }
}