package com.sappyllc.customer_api.controllers;

import com.sappyllc.customer_api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/api/customers")
    public String getAllCustomers() throws IOException {
        return customerService.getAllCustomers();
    }

}
