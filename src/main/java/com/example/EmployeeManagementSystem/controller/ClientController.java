package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.ClientDTO;
import com.example.EmployeeManagementSystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<String> createClient(@RequestBody ClientDTO dto) {

        if(dto.getName() == null || dto.getName().isEmpty())
        {
            ResponseEntity.badRequest().body("provide Name !");
        }

        clientService.createClient(dto);
        return ResponseEntity.status(201).body("client successfully save");
    }

    @GetMapping("/get")
    public ResponseEntity<ClientDTO> getClient(@RequestParam Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateClient(@RequestParam Long id, @RequestBody ClientDTO dto) {

       clientService.updateClient(id, dto);
        return ResponseEntity.status(200).body("client updated") ;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClient(@RequestParam Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully");
    }
}
