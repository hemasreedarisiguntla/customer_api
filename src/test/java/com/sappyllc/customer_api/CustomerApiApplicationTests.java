package com.sappyllc.customer_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sappyllc.customer_api.vo.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CustomerApiApplicationTests {

	ObjectMapper mapper;
	ArrayList<Customer> customerList;

	String customersJsonPath = "src/test/data/customers.json"; // 4 customers
	String customerJsonPath = "src/test/data/existingCustomer.json"; // 1 customer
	String newCustomerJsonPath = "src/test/data/newCustomer.json"; // 1 customer

	@BeforeEach
	void setUp() throws IOException {
		initializeCustomersData();
	}

	@Autowired
	MockMvc mockMvc;


	@Test
	void contextLoads() {
	}

	@Test
	public void getAllCustomersTest() throws Exception {
		mockMvc.perform(get("/api/customers"))
				.andExpect(status().isOk())
				.andExpect(content().string(mapper.writeValueAsString(customerList)));
	}

	@Test
	public void getACustomer() throws Exception {
		mockMvc.perform(get("/api/customers/{id}","b8a504e8-7cbd-4a54-9a24-dc1832558162"))
				.andExpect(status().isOk())
				.andExpect(content().string(getCustomerJsonString()));
	}

	@Test
	public void addANewCustomer() throws Exception {
		mockMvc.perform(post("/api/customers")
								.contentType(MediaType.APPLICATION_JSON)
								.characterEncoding("utf-8")
								.content(createCustomerJsonString())
				)
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Araminta"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ross"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("309-555-1370"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1849 Harriet Ave, Auburn, NY 63102"));
	}


	// TEST UTILITIES ----------------------------------------------------

	private void initializeCustomersData() throws IOException {
		mapper = new ObjectMapper();
		File customersFile = new File(customersJsonPath);
		customerList = mapper.readValue(customersFile, new TypeReference<ArrayList<Customer>>() {});
	}

	private String getCustomerJsonString() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File customerFile = new File(customerJsonPath);
		Customer customer = mapper.readValue(customerFile, Customer.class);
		return mapper.writeValueAsString(customer);
	}

	private String createCustomerJsonString() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File customerFile = new File(newCustomerJsonPath);
		Customer customer = mapper.readValue(customerFile, Customer.class);
		return mapper.writeValueAsString(customer);
	}

}
