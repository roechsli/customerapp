package com.acn.Service;

import java.util.List;

import com.acn.CustomerDao;
import com.model.Customer;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao custdao;
	public CustomerServiceImpl(CustomerDao custdao) {
		super();
		this.custdao = custdao;
	}
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Customer> getAllCustomer() {
		return custdao.getAllCustomer();
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
