package com.sappyllc.customer_api.services;

import java.io.IOException;

public interface CustomerService {
    String getAllCustomers() throws IOException;

    String getACustomer(String id) throws IOException;
}
