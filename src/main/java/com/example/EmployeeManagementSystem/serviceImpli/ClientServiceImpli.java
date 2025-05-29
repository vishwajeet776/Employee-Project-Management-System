package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.AddressRepository;
import com.example.EmployeeManagementSystem.Repository.ClientRepository;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.dto.ClientDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.entity.Client;
import com.example.EmployeeManagementSystem.mapper.ClientMapper;
import com.example.EmployeeManagementSystem.service.ClientService;
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
        Client existingClient = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("client not foundd with ID:" + id));

        /*    existing.setName(dto.getName());           //  this type use then compulsory to assign value to all field if not assign then  "null store"
           existing.setContractStartDate(dto.getContractStartDate());
        */
        //email, phone, contractEndDate we able to update bez we use " if( !=null )" block or not assign va lue then privious value store & not give null
        // able to change 1 or all 3 field. other field set privious data.

        // Update simple fields
        if (dto.getEmail() != null) {
            existingClient.setEmail(dto.getEmail());
        }

        if (dto.getPhone() != null) {
            existingClient.setPhone(dto.getPhone());
        }

        if (dto.getContractEndDate() != null) {
            existingClient.setContractEndDate(dto.getContractEndDate());
        }

        // Update nested address fields all provided  field  update only
        if (dto.getAddressDto() != null) {
            AddressDTO addressDto = dto.getAddressDto();
            Address address = existingClient.getAddress();

            if (address != null) {
                address = new Address(); // or throw exception if address should always exist
            }

            if (addressDto.getPincode() != null) {
                address.setPincode(addressDto.getPincode());
            }

            if (addressDto.getCity() != null) {
                address.setCity(addressDto.getCity());
            }

            if (addressDto.getState() != null) {
                address.setState(addressDto.getState());
            }

            existingClient.setAddress(address);
        }

        return clientMapper.entityToDto(clientRepo.save(existingClient));
    }

    @Override
    public void deleteClient(Long id) {

        clientRepo.deleteById(id);
    }
}
