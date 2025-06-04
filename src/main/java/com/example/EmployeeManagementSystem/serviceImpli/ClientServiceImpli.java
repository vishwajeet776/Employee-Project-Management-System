package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.AddressRepository;
import com.example.EmployeeManagementSystem.Repository.ClientRepository;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.dto.ClientDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.entity.Client;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourseNotFoundException;
import com.example.EmployeeManagementSystem.mapper.ClientMapper;
import com.example.EmployeeManagementSystem.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpli implements ClientService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private AddressRepository AddressRepo;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDTO createClient(ClientDTO dto) {

        log.info("createClient - Saving client: {}", dto.getName());
        Client client = clientMapper.dtoToEntity(dto);

        log.info("createClient - Client saved with ID: {}\", saved.getId()");
        return clientMapper.entityToDto(clientRepo.save(client));
    }

    @Override
    public List<ClientDTO> getAllClients() {

        log.info("getAllClients - Fetching all clients");
        return clientRepo.findAll().stream().map(clientMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {

        log.info("getClientById - Fetching client with ID: {}", id);
        Client client = clientRepo.findById(id).orElseThrow(()->{

            log.info("getClientById - Client not found with ID: {}\", id");
           return new  ResourseNotFoundException ("! client Not Found With ID: "+id);
        });

        log.info("getClientById - Client found: {}", client.getName());
        return clientMapper.entityToDto(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {

        log.info("updateClient - Updating client with ID: {}", id);
        Client existingClient = clientRepo.findById(id).orElseThrow(() -> new ResourseNotFoundException("! client not foundd with ID:" + id));

        /*    existing.setName(dto.getName());           //  this type use then compulsory to assign value to all field if not assign then  "null store"
           existing.setContractStartDate(dto.getContractStartDate());
        */
        // * email, phone, contractEndDate & all Address fields * we able to update bez we use " if( !=null )" block or not assign va lue then privious value store & not give null
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

        // Update AddressDTo object nested address fields all provided  field  update only
        if (dto.getAddressDto() != null) {
            AddressDTO addressDto = dto.getAddressDto();
            Address address = existingClient.getAddress();


            if(addressDto.getAddress()!=null)
            {
              address.setAddress( addressDto.getAddress() ); ;
            }

            if (addressDto.getCity() != null) {
                address.setCity(addressDto.getCity());
            }

            if (addressDto.getState() != null) {
                address.setState(addressDto.getState());
            }

            if (addressDto.getPincode() != null) {
                address.setPincode(addressDto.getPincode());
            }


            existingClient.setAddress(address);
        }
        log.info("updateClient - Client updated with ID: {}", dto.getId());
        return clientMapper.entityToDto(clientRepo.save(existingClient));
    }


    @Override
    public void deleteClient(Long id) {

        log.info("deleteClient - Deleting client with ID: {}", id);
        clientRepo.findById(id).orElseThrow(()->new RuntimeException("! client Not Fouund ID:" + id));

        log.info("deleteClient - Client deleted successfully with ID: {}", id);
        clientRepo.deleteById(id);
    }
}
