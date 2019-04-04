
package com.acn.Service;

import java.util.List;

import com.model.Customer;

public interface ICustomerService {
	List<Customer> getAllCustomer();
	List<Customer> getCustomerByName(String fname, String lname);
	Customer getCustomerById(Long id);

	void addCustomer(Customer cust);
	void changeCustomer(Customer cust);
	void deleteCustomer(Customer cust);

}
>>>>>>> 1f4114a7b87d71589a92f3f7b48086ef21bf8ef9
