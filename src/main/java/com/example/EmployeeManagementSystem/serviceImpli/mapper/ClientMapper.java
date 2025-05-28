package com.example.EmployeeManagementSystem.serviceImpli.mapper;

import com.example.EmployeeManagementSystem.Repository.AddressRepository;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.ProjectRepository;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.dto.ClientDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.entity.Client;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientMapper {

    @Autowired
    private AddressRepository addressRepo;

    public ClientDTO entityToDto(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setContractStartDate(client.getContractStartDate());
        dto.setContractEndDate(client.getContractEndDate());

        if(client.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();

            addressDTO.setAddress(client.getAddress().getAddress());
            addressDTO.setCity(client.getAddress().getCity());
            addressDTO.setState(client.getAddress().getState());
            addressDTO.setPincode(client.getAddress().getPincode());

            dto.setAddressDto(addressDTO);
        }
        return dto;
    }

    public Client dtoToEntity(ClientDTO dto) {
        Client client = new Client();

        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setContractStartDate(dto.getContractStartDate());
        client.setContractEndDate(dto.getContractEndDate());

        if (dto.getAddressDto() != null) {
            Address address = new Address();

            address.setAddress( dto.getAddressDto().getAddress() );
            address.setCity(dto.getAddressDto().getCity());
            address.setState(dto.getAddressDto().getState());
            address.setPincode(dto.getAddressDto().getPincode());

            client.setAddress(address);

         /*   Address address = addressRepo.findById(dto.getAddressDto().getId())
                    .orElseThrow(() -> new RuntimeException("Address not found"));
            client.setAddress(address);

          */
        }
        return client;
    }

}