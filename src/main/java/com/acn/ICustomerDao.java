package com.acn;

import java.util.List;

import com.model.Customer;


public interface ICustomerDao {
	List<Customer> getAllCustomers();
	List<Customer> getCustomerByName(String fname, String lname);
	Customer getCustomerById(Long id);
	
	void addCustomer(Customer cust);
	void changeCustomer(Customer cust);
	void deleteCustomer(Customer cust);
}
