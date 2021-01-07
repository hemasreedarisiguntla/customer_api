package com.sappyllc.customer_api.controllers;

import com.sappyllc.customer_api.services.CustomerService;
import com.sappyllc.customer_api.vo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/api/customers")
    public String getAllCustomers() throws IOException {
        return customerService.getAllCustomers();
    }

    @GetMapping("/api/customers/{id}")
    public String getACustomer(@PathVariable String id) throws IOException {
        return customerService.getACustomer(id);
    }

    @PostMapping("/api/customers")
    public ResponseEntity<Customer> addANewCustomer(@RequestBody Customer customer) throws IOException {
        Customer newCustomer=customerService.addANewCustomer(customer);

        if(newCustomer!=null) {
            return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
