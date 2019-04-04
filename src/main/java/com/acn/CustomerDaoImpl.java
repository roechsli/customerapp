package com.acn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Address;
import com.model.Customer;

/*
 This is the Data Access Object for the class Customer
 Methods:
 */
public class CustomerDaoImpl implements ICustomerDao {
	private MyConnection myConn;
	
	public CustomerDaoImpl() {
		myConn = new MyConnection();
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> myCustList = new ArrayList<>();
		String sqlStr = "select * from customer";
		Customer newCust = null;
		Address newAddress = null;
		
		try (Connection conn = myConn.getMyConnection()){
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sqlStr);
			try {
				while (rs.next()) {
					newCust = getCustFromRs(rs);
//					readAddressForCustomer(newCust, conn);
					myCustList.add(newCust);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCustList;
	}

	@Override
	public List<Customer> getCustomerByName(String fname, String lname) {
		List<Customer> myCustList = new ArrayList<>();
		Customer newCust = null;
		String sqlStr = "select * from customer where lname LIKE ? and fname LIKE ?";
		
		ResultSet rs = null;
		try (Connection conn = myConn.getMyConnection()){
			PreparedStatement stmt = conn.prepareStatement(sqlStr);
			stmt.setString(1, lname);
			stmt.setString(2, fname);
			rs = stmt.executeQuery();
			
			try {
				while (rs.next()) {
					newCust = getCustFromRs(rs);
//					readAddressForCustomer(newCust, conn);
					myCustList.add(newCust);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCustList;
	}

	@Override
	public Customer getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCustomer(Customer cust) {
		String sqlStr = "insert into customer (fname, lname) values (?,?)";
		ResultSet rs = null;
		
		try (Connection conn = myConn.getMyConnection()){
			PreparedStatement stmt = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cust.getFirstName());
			stmt.setString(2, cust.getLastName());
			int numOfRows = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				cust.setId(rs.getLong(1)); // first column in this row is PK
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void changeCustomer(Customer cust) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(Customer cust) {
		// TODO Auto-generated method stub
		
	}
	
	private Customer getCustFromRs(ResultSet rs) {
		Customer newCust = null;
		Address newAddress = null;
		try {
			newCust = new Customer(
					rs.getLong("id"),
					rs.getString("fname"),
					rs.getString("lname"),
					rs.getString("phone"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCust;
	}

}
