package com.example.demo.testcontainers.controller;

import com.example.demo.testcontainers.dao.CustomerDao;
import com.example.demo.testcontainers.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class CustomerController {

    private CustomerDao customerDao;

    @PostMapping(path = "/api/customer")
    public Customer createCustomer(@RequestBody Customer customer){

        log.info("saving [{}]", customer);
        Long persistedCustomerId = customerDao.save(customer);
        log.info("returning [{}]", persistedCustomerId);

        return customerDao.findById(persistedCustomerId).get();
    }

    @GetMapping(path = "/api/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer customerId){

        log.info("retrieving customer Id [{}]", customerId);
        Optional<Customer> customer = customerDao.findById(Long.valueOf(customerId));
        log.info("returning [{}]", customer);

        return customer.orElseThrow(() -> new RuntimeException("customer not found for Id " + customerId));
    }

}
