package com.example.bookstroreapplication.model;
import com.example.bookstroreapplication.dto.CustomerDTO;
import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "addressBookDetails")
public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "addressBook_id")
        private int id;
        private String fullname;
        private String phonenumber;
        private String address;
        private String email;
        private String city;
        private String state;
        private String zipcode;


        public Customer(CustomerDTO customerDTO) {
            this.updateAddressBookData(customerDTO);
        }

        public Customer() {
        }

        public void updateAddressBookData(CustomerDTO customerDTO) {
            this.fullname = customerDTO.getFullname();
            this.phonenumber = customerDTO.getPhonenumber();
            this.address = customerDTO.getAddress();
            this.email = customerDTO.getEmail();
            this.city = customerDTO.getCity();
            this.state = customerDTO.getState();
            this.zipcode = customerDTO.getZipcode();

        }

        public void getPassword() {

        }
    }

