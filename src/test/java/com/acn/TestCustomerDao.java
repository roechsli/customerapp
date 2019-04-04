package com.acn;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
		fail("Not yet implemented");
	}
	@Test
	public void testCustomerByName() {
		fail("Not yet implemented");
	}

}
