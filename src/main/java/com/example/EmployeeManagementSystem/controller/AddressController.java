package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity< AddressDTO > create(@RequestBody AddressDTO dto) {

        log.info("create - Address Created {}",dto);
        return ResponseEntity.ok( addressService.createAddress(dto) );
    }

    @PutMapping("/update")
    public ResponseEntity< AddressDTO > update(@RequestParam Long id, @RequestBody AddressDTO dto) {

        log.info("update- Address updating {} ", id);
        return ResponseEntity.ok(addressService.updateAddress(id, dto)) ;
    }

    @GetMapping("/get")
    public AddressDTO getById(@RequestParam Long id)
    {
        log.info("getById - Address retrieved successfully for ID: {}", id);
        return   addressService.getAddressById(id);
    }

    @GetMapping("/list")
    public ResponseEntity< List<AddressDTO>  > getAll() {

        log.info("list- fetching All Addresses List");
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    //  204 No Content: Successfully deleted, no response body
    @DeleteMapping("/delete")
    public ResponseEntity< String > delete(@RequestParam Long id) {

        log.info("delete- address deleting{} ", id);
        addressService.deleteAddress(id);
        log.info("delete successfully");
        return ResponseEntity.noContent().build();  //204 no content
    }
}