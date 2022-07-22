package com.example.bookstroreapplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
@Data
public class CustomerDTO {
        @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message ="Addressbook author first name pattern is invalid")
        private String fullname;
        @NotNull(message = "phone number should not be empty")
        private String phonenumber;
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Email pattern is invalid")
        private String email;
        @NotBlank(message = "address should not be empty")
        private String address;
        @NotBlank(message = "city should not be empty")
        private String city;
        @NotBlank(message = "state should not be empty")
        public String state;
        @Pattern(regexp = "^[0-9]{6}$", message = "zipcode pattern is invalid")
        private String zipcode;
    }

