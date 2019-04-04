package com.acn;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.Customer;

public class TestCustomerDao {
	private static MyConnection myConn;
	private static ICustomerDao custDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myConn = new MyConnection();
		custDao = new CustomerDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myConn = null;
		custDao = null;
	}

	@Test
	public void testAddCustomer() {
		Customer myCust = new Customer("Disslon", "Eklon");
		custDao.addCustomer(myCust);
		assertNotNull(custDao);
//		List<Customer> myCustList = custDao.getCustomerByName("%lon","%lon");
//		myCustList.forEach(System.out::println);
//		assertNotNull(myCust.getId());
	}
	@Test
	public void testCustomerByName() {
		List<Customer> myCustList = custDao.getCustomerByName("%lon","%lon");
		myCustList.forEach(System.out::println);
	}

}
