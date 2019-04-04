package com.acn.Service;

import java.util.List;

import com.acn.CustomerDaoImpl;
import com.model.Customer;

public class CustomerServiceImpl implements ICustomerService {
	private CustomerDaoImpl custdao;
	public CustomerServiceImpl(CustomerDaoImpl custdao) {
		super();
		this.custdao = custdao;
	}
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Customer> getAllCustomer() {
		return custdao.getAllCustomers();
	}
	@Override
	public List<Customer> getCustomerByName(String fname, String lname) {
		return custdao.getCustomerByName(fname, lname);
	}
	@Override
	public Customer getCustomerById(Long id) {
		return custdao.getCustomerById(id);
	}
	@Override
	public void addCustomer(Customer cust) {
		custdao.addCustomer(cust);
		
	}
	@Override
	public void changeCustomer(Customer cust) {
		custdao.changeCustomer(cust);
		
	}
	@Override
	public void deleteCustomer(Customer cust) {
		custdao.deleteCustomer(cust);
		
	}

}
