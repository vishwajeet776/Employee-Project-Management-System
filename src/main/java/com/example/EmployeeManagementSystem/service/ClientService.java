package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    public ClientDTO createClient(ClientDTO dto);

    public List<ClientDTO> getAllClients();

    public ClientDTO getClientById(Long id);

    public ClientDTO updateClient(Long id, ClientDTO dto);

    public void deleteClient(Long id);
}
