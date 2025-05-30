package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity< AddressDTO > create(@RequestBody AddressDTO dto) {

        return ResponseEntity.ok( addressService.createAddress(dto) );
    }

    @PutMapping("/update")
    public AddressDTO update(@RequestParam Long id, @RequestBody AddressDTO dto) {
        return addressService.updateAddress(id, dto);
    }

    @GetMapping("/get")
    public AddressDTO getById(@RequestParam Long id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("/list")
    public ResponseEntity< List<AddressDTO>  > getAll() {

        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    //  204 No Content: Successfully deleted, no response body
    @DeleteMapping("/delete")
    public ResponseEntity< String > delete(@RequestParam Long id) {

        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();  //204 no content
    }
}