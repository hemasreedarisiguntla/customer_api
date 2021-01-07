package com.sappyllc.customer_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {

    //private List<Customer> customerList;
//    @Autowired
//    private ObjectMapper mapper;
    @Autowired
    private CustomerService customerService;

//    CustomerController(List<Customer> customerList) {
//        this.customerList = customerList;
//    }
    
    @GetMapping("/api/customers")
    public String getAllCustomers() throws IOException {
        return customerService.getAllCustomers();
    }

}
