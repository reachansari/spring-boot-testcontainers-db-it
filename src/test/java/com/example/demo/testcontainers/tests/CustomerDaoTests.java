package com.example.demo.testcontainers.tests;

import com.example.demo.testcontainers.CustomMySqlContainer;
import com.example.demo.testcontainers.dao.CustomerDao;
import com.example.demo.testcontainers.entity.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@Testcontainers
class CustomerDaoTests {

	@Autowired
	private CustomerDao customerDao;

	@Container
	public static CustomMySqlContainer mySqlContainer =  CustomMySqlContainer.getInstance()
															.withInitScript("database/customer-schema.sql");

	@Test
	public void should_returnCustomers_with_LastNameJones() {

		List<Customer> customers = customerDao.findByLastName("jones");

		assertThat(customers.get(0).getFirstName(), is("Jack"));
		assertThat(customers.get(0).getLastName(), is("jones"));
		assertThat(customers.get(0).getDob(), is(LocalDate.of(1990, 01, 11)));
		assertThat(customers.get(0).getGender(), is("male"));
	}

	@Test
	public void should_returnSavedCustomer() {

		Customer customer = new Customer();
		customer.setFirstName("Olivia");
		customer.setLastName("mia");
		customer.setDob(LocalDate.of(2001, 10, 19));
		customer.setGender("female");

		customerDao.save(customer);

		List<Customer> customers = customerDao.findByLastName("mia");

		assertThat(customers.get(0).getFirstName(), is("Olivia"));
		assertThat(customers.get(0).getLastName(), is("mia"));
		assertThat(customers.get(0).getDob(), is(LocalDate.of(2001, 10, 19)));
		assertThat(customers.get(0).getGender(), is("female"));
	}

}