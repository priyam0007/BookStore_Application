package com.example.bookstroreapplication.controller;

import com.example.bookstroreapplication.dto.CustomerDTO;
import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.model.Customer;
import com.example.bookstroreapplication.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(allowedHeaders="*",origins="*")
@RestController
@RequestMapping("/addressbook")
public class CustomerController {
        @Autowired
        private ICustomerService iCustomerService;

    @GetMapping("/getAllCustomer")
    public ResponseEntity<ResponseDTO> getAllCustomer(){
        List<Customer> customer = iCustomerService.getAllDetail();
        ResponseDTO responseDTO = new ResponseDTO("Get Customer Detail SuccessFully",customer);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<ResponseDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = iCustomerService.addNewCustomer(customerDTO);
        ResponseDTO responseDTO = new ResponseDTO("New Customer Added Successfully",customer);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}