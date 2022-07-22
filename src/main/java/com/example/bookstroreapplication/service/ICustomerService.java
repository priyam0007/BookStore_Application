package com.example.bookstroreapplication.service;
import com.example.bookstroreapplication.dto.CustomerDTO;
import com.example.bookstroreapplication.model.Customer;

import java.util.List;
public interface ICustomerService {


        List<Customer> getAllDetail();
        Customer addNewCustomer(CustomerDTO customerDTO);

    }

