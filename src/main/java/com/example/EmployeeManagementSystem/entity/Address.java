package com.example.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String address;
        private String city;
        private String state;
        private String pincode;

}
/* postman

{
  "address": "1234 Elm Street",
  "city": "Springfield",
  "state": "Illinois",
  "pincode": "62704"
}

 */

