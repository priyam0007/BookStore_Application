package com.example.bookstroreapplication.repository;


import com.example.bookstroreapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {

    }


