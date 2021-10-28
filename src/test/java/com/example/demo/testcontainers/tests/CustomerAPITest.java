package com.example.demo.testcontainers.tests;

import com.example.demo.testcontainers.CustomMySqlContainer;
import com.example.demo.testcontainers.entity.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerAPITest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Container
	public static CustomMySqlContainer mySqlContainer =  CustomMySqlContainer.getInstance()
															.withInitScript("database/customer-schema.sql");

	@Test
	public void should_returnCustomer_forCustomerId_1() {

		ResponseEntity<Customer> customerResponse = restTemplate.getForEntity("/api/customer/1", Customer.class);

		assertThat(customerResponse.getStatusCode(), is(HttpStatus.OK));

		assertThat(customerResponse.getBody().getFirstName(), is("Jack"));
		assertThat(customerResponse.getBody().getLastName(), is("jones"));
		assertThat(customerResponse.getBody().getDob(), is(LocalDate.of(1990, 01, 11)));
		assertThat(customerResponse.getBody().getGender(), is("male"));
	}

	@Test
	public void should_saveCustomer_andReturnNewEntity() {

		Customer customer = new Customer();
		customer.setFirstName("Olivia");
		customer.setLastName("mia");
		customer.setDob(LocalDate.of(2001, 10, 19));
		customer.setGender("female");

		HttpEntity customerEntity = new HttpEntity<>(customer);

		ResponseEntity<Customer> savedCustomerResponse = restTemplate.postForEntity("/api/customer", customerEntity, Customer.class);

		assertThat(savedCustomerResponse.getBody().getId(), is(notNullValue()));
		assertThat(savedCustomerResponse.getBody().getFirstName(), is("Olivia"));
		assertThat(savedCustomerResponse.getBody().getLastName(), is("mia"));
		assertThat(savedCustomerResponse.getBody().getDob(), is(LocalDate.of(2001, 10, 19)));
		assertThat(savedCustomerResponse.getBody().getGender(), is("female"));
	}

}