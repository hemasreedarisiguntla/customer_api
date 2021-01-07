package com.sappyllc.customer_api.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sappyllc.customer_api.vo.Customer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@Component
public class CustomerServiceImpl implements CustomerService {

    String customersJsonPath = "src/test/data/customers.json"; // 4 customers
    String customerJsonPath = "src/test/data/existingCustomer.json"; // 1 customer
    String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer

    @Override
    public String getAllCustomers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File customersFile = new File(customersJsonPath);
        ArrayList<Customer> customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {
        });
        return mapper.writeValueAsString(customerList);
    }
}
