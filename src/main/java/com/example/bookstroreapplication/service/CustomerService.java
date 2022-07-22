package com.example.bookstroreapplication.service;
import com.example.bookstroreapplication.dto.CustomerDTO;
import com.example.bookstroreapplication.model.Customer;
import com.example.bookstroreapplication.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllDetail() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addNewCustomer(CustomerDTO customerDTO) {
        Customer customerData = new Customer(customerDTO);
        log.debug("cutomerData" + customerData.toString());
        return customerRepository.save(customerData);
    }
}