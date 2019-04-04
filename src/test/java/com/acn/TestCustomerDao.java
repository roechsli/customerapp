package com.acn;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.model.Customer;
import com.model.ICustomer;

public class TestCustomerDao {
	private static MyConnection myConn;
	private static ICustomerDao custDao;
	private static AnnotationConfigApplicationContext ctw;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myConn = new MyConnection();
		custDao = new CustomerDaoImpl();
		ctw = new AnnotationConfigApplicationContext(BeanConfig.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myConn = null;
		custDao = null;
		((ConfigurableApplicationContext)ctw).close();
	}

	@Test @Ignore
	public void testAddCustomer() {
		Customer myCust = new Customer("Disslon", "Eklon");
		custDao.addCustomer(myCust);
		assertNotNull(custDao);
	}
	@Test @Ignore
	public void testCustomerByName() {
		List<Customer> myCustList = custDao.getCustomerByName("%lon","%lon");
		myCustList.forEach(System.out::println);
		assertNotNull(custDao);
	}
	@Test 
	public void testBeansCustDao() {
		ICustomerDao custDaoBean = (ICustomerDao) ctw.getBean("custDao");
		custDaoBean.getAllCustomers().forEach(System.out::println);
	}
	@Test 
	public void testBeansCusts() {
		ICustomerDao custDaoBean = (ICustomerDao) ctw.getBean("custDao");
		for (int i = 1; i<5; i++) {
			String pers = "pers" + i;
			ICustomer thisCust = (ICustomer) ctw.getBean(pers);
			custDaoBean.addCustomer((Customer)thisCust);
		}
		List<Customer> myCustList = custDao.getAllCustomers();
		myCustList.forEach(System.out::println);
	}
	@Test @Ignore
	public void testDeleteCustomers() {
		ICustomerDao custDaoBean = (ICustomerDao) ctw.getBean("custDao");
		assertNotNull(custDaoBean.getAllCustomers());
		List<Customer> myCustList = custDaoBean.getAllCustomers();
		for (Customer cust : myCustList) {
			custDaoBean.deleteCustomer(cust);
		}
		assertTrue(custDaoBean.getAllCustomers().isEmpty());
		
	}
	@Test 
	public void testCustDaoBean() {
		ICustomerDao custDaoBean = (ICustomerDao) ctw.getBean("custDao2");
		assertNotNull(custDaoBean.getAllCustomers());
		List<Customer> myCustList = custDaoBean.getAllCustomers();
		for (Customer cust : myCustList) {
			custDaoBean.deleteCustomer(cust);
		}
		assertTrue(custDaoBean.getAllCustomers().isEmpty());
		
	}

}
