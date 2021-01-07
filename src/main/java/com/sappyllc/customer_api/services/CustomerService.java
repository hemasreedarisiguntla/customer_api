package com.sappyllc.customer_api.services;

import com.sappyllc.customer_api.vo.Customer;

import java.io.IOException;

public interface CustomerService {
    String getAllCustomers() throws IOException;

    String getACustomer(String id) throws IOException;

    Customer addANewCustomer(Customer customer) throws IOException;
}
