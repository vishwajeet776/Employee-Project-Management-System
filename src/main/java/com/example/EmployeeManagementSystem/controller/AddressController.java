package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "createing new Address ", description = "saving new Address from Employee & Client Class By Passing Address object")
    @ApiResponses( value = {
         @ApiResponse(   responseCode ="201",description ="CREATED",
    content = @Content(schema = @Schema(implementation = Address.class))),
            @ApiResponse(responseCode = "404", description="Address Not Found")
})
    public ResponseEntity< AddressDTO > create(@RequestBody AddressDTO dto) {

        log.info("create - Address Created {}",dto);
        return ResponseEntity.ok( addressService.createAddress(dto) );
    }

    @Operation(
            summary="Update Address By ID",
            description="Using ID method update address Details "
    )
    @PutMapping("/update")
    public ResponseEntity< AddressDTO > update(@RequestParam Long id, @RequestBody AddressDTO dto) {

        log.info("update- Address updating {} ", id);
        return ResponseEntity.ok(addressService.updateAddress(id, dto)) ;
    }

    @Operation(
            summary="Fetching Address By ID",
            description="Using ID get Address "
    )
    @GetMapping("/get")
    public AddressDTO getById(@RequestParam Long id)
    {
        log.info("getById - Address retrieved successfully for ID: {}", id);
        return   addressService.getAddressById(id);
    }

    @Operation(
            summary="List of Address",
            description="fetching List of Address "
    )
    @GetMapping("/list")
    public ResponseEntity< List<AddressDTO>  > getAll() {

        log.info("list- fetching All Addresses List");
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @Operation(
            summary="Delete Address",
            description="Using ID Deleteing Address"
    )
    //  204 No Content: Successfully deleted, no response body
    @DeleteMapping("/delete")
    public ResponseEntity< String > delete(@RequestParam Long id) {

        log.info("delete- address deleting{} ", id);
        addressService.deleteAddress(id);
        log.info("delete successfully");
        return ResponseEntity.noContent().build();  //204 no content
    }
}