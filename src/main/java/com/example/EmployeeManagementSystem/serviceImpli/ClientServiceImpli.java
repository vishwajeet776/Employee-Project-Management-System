package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.AddressRepository;
import com.example.EmployeeManagementSystem.Repository.ClientRepository;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.dto.ClientDTO;
import com.example.EmployeeManagementSystem.entity.Client;
import com.example.EmployeeManagementSystem.service.ClientService;
import com.example.EmployeeManagementSystem.serviceImpli.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpli implements ClientService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private AddressRepository AddressRepo;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDTO createClient(ClientDTO dto) {
        Client client = clientMapper.dtoToEntity(dto);
        return clientMapper.entityToDto(clientRepo.save(client));
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepo.findAll().stream().map(clientMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepo.findById(id).orElseThrow();
        return clientMapper.entityToDto(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client existing = clientRepo.findById(id).orElseThrow();
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setContractStartDate(dto.getContractStartDate());
        existing.setContractEndDate(dto.getContractEndDate());
        return clientMapper.entityToDto(clientRepo.save(existing));
    }

    @Override
    public void deleteClient(Long id) {

        clientRepo.deleteById(id);
    }
}
