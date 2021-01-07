package com.sappyllc.customer_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Component
@Service
public class CustomerService {

    String customersJsonPath = "src/test/data/customers.json"; // 4 customers
    String customerJsonPath = "src/test/data/existingCustomer.json"; // 1 customer
    String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer

    public String getAllCustomers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File customersFile = new File(customersJsonPath);
        ArrayList<Customer> customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {
        });
        return mapper.writeValueAsString(customerList);
    }
}
