package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public AddressDTO create(@RequestBody AddressDTO dto) {
        return addressService.createAddress(dto);
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
    public List<AddressDTO> getAll() {
        return addressService.getAllAddresses();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        addressService.deleteAddress(id);
    }
}