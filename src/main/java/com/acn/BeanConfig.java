package com.acn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.acn.Service.CustomerServiceImpl;
import com.acn.Service.ICustomerService;
import com.model.Customer;
import com.model.ICustomer;

@Configuration
@ComponentScan
public class BeanConfig {
	@Bean(name="pers1")
	public ICustomer customer1() {
		return new Customer("Paul", "Panzer");
	}
	@Bean(name="pers2")
	public ICustomer customer2() {
		return new Customer("Friedrich", "Schimmel");
	}
	@Bean(name="pers3")
	public ICustomer customer3() {
		return new Customer("Arnold", "Fummel");
	}
	@Bean(name="pers4")
	public ICustomer customer4() {
		return new Customer("Peter", "Pilz");
	}
	@Bean(name="custDao")
	public ICustomerDao customerDao() {
		return new CustomerDaoImpl();
	}
	@Bean(name="custDao2")
	public ICustomerDao customerDao2() {
		return new CustomerDaoImpl(myConn());
	}
	@Bean
	public MyConnection myConn() {
		return new MyConnection();
	}
	@Bean
	public ICustomerService custServ() {
		return new CustomerServiceImpl(customerDao2());
	}
}