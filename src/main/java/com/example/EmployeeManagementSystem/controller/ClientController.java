package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.ClientDTO;
import com.example.EmployeeManagementSystem.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/create")
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
    public ResponseEntity<ClientDTO> getClient(@RequestParam Long id) {

        log.info("controller/getClient - client ID pass to Service: {}", id);
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientDTO>> getAllClients() {

        log.info("getAllClients - Fetching all clients");
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateClient(@RequestParam Long id, @RequestBody ClientDTO dto) {

        log.info("updateClient - Updating client ID: {}", id);
       clientService.updateClient(id, dto);

        log.info("updateClient - Client updated successfully");
        return ResponseEntity.status(200).body("client updated") ;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClient(@RequestParam Long id) {

        log.info("deleteClient - client deleating id:{}",id);
        clientService.deleteClient(id);

        log.info("client successfully deleted {}",id);
        return ResponseEntity.ok("Client deleted successfully");
    }
}
