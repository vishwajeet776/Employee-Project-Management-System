package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.ClientDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.entity.Client;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/client")
@Slf4j
@Tag(name=" Client API")                                  //Swagger= name change client-Controller TO Client API use @tag
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping("/create")                       //Swagger @operation Providing method summary(name) & desciption
    @Operation(
            summary = "create new Client ", description = "saving new Client Class &  Passing Address object ( Address Details )")
    @ApiResponses( value = {
            @ApiResponse(   responseCode ="201",description ="CREATED",
                    content = @Content(schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description="Client Not Created")
    })
    public ResponseEntity<String> createClient(@RequestBody ClientDTO dto) {

        log.info("createClient- cheking Name & Salary Not Null" );
        if(dto.getName() == null || dto.getName().isEmpty())
        {
            log.warn("createClient - Client name & salare is missing");
            ResponseEntity.badRequest().body("provide Name & Salary!");
        }

        clientService.createClient(dto);
        log.info("createClient - Client created successfully: {}", dto.getName());

        return ResponseEntity.status(201).body("client successfully save");

    }


    @GetMapping("/get")
    @Operation(
            summary ="Get Client By ID", description = "This method to fetch client using id only",
    responses = {
            @ApiResponse (  responseCode = "201" , description = "Successfully Featched" , content = @Content(schema = @Schema (implementation = Client.class))) ,
            @ApiResponse(responseCode = "404" , description = "client not Fond")
            } )
    public ResponseEntity<ClientDTO> getClient(@RequestParam Long id) {

        log.info("controller/getClient - client ID pass to Service: {}", id);
        return ResponseEntity.ok(clientService.getClientById(id));
    }


    @GetMapping("/list")
    @Operation(
            summary =" List of Client", description = " method provide all client List"  )
    @ApiResponse(
            responseCode = "201" , description = "Successfully Featched"  )
    public ResponseEntity<List<ClientDTO>> getAllClients() {

        log.info("getAllClients - Fetching all clients");
        return ResponseEntity.ok(clientService.getAllClients());
    }



    @PutMapping("/update")
    @Operation(
            summary ="Updating Client",
            description = "method update client Details using id" )
    @ApiResponse(responseCode = "200" , description = "updated Client details")
    public ResponseEntity<String> updateClient(@RequestParam Long id, @RequestBody ClientDTO dto) {

        log.info("updateClient - Updating client ID: {}", id);
       clientService.updateClient(id, dto);

        log.info("updateClient - Client updated successfully");
        return ResponseEntity.status(200).body("client updated") ;
    }


    @DeleteMapping("/delete")
    @Operation(
            summary ="Delete Client using id", description = " client deleated using id", responses = {
                    @ApiResponse(responseCode = "200" , description = "client deleated successfully" , content = @Content(schema = @Schema(implementation = Client.class) ) ),
                            @ApiResponse(responseCode = "404", description = "Client not Found For delete" )})
    public ResponseEntity<String> deleteClient(@RequestParam Long id) {

        log.info("deleteClient - client deleating id:{}",id);
        clientService.deleteClient(id);

        log.info("client successfully deleted {}",id);
        return ResponseEntity.ok("Client deleted successfully");
    }
}
