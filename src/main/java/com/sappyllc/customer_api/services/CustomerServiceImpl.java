package com.sappyllc.customer_api.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sappyllc.customer_api.vo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@Component
public class CustomerServiceImpl implements CustomerService {

    String customersJsonPath = "src/test/data/customers.json"; // 4 customers
    String customerJsonPath = "src/test/data/existingCustomer.json"; // 1 customer
    String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer
    @Autowired
    ObjectMapper mapper;

    @Override
    public String getAllCustomers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Customer> customerList = getCustomerArrayList(mapper);
        return mapper.writeValueAsString(customerList);
    }

    private ArrayList<Customer> getCustomerArrayList(ObjectMapper mapper) throws IOException {
        File customersFile = new File(customersJsonPath);
        ArrayList<Customer> customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {
        });
        return customerList;
    }

    @Override
    public String getACustomer(String id) throws IOException {
        ArrayList<Customer> customerList = getCustomerArrayList(mapper);
        for (Customer customer:customerList) {
            if(customer.getId().equals(id)) {
                return mapper.writeValueAsString(customer);
            }

        }
        return "Customer not found";
    }

    @Override
    public Customer addANewCustomer(Customer customer) throws IOException {
        Customer newCustomer = new Customer(customer.getFirstName(),
                customer.getLastName(),customer.getPhoneNumber(),customer.getAddress());
        ArrayList<Customer> customerList = getCustomerArrayList(mapper);
        customerList.add(newCustomer);
        return newCustomer;
    }
}
