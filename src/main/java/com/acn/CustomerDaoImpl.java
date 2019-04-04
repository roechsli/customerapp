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
		// should be give as argument in constructor call
//		myConn = new MyConnection();
	}

	public CustomerDaoImpl(MyConnection myConn) {
		super();
		this.myConn = myConn;
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
		Customer myCustomer = null;
		String sqlStr = "select * from customer where customer.id = ?";
		ResultSet rs = null;

		try (Connection conn = myConn.getMyConnection()){
			PreparedStatement stmt = conn.prepareStatement(sqlStr);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			try {
				if (rs.next()) {
					myCustomer = getCustFromRs(rs);
//					readAddressForCustomer(myCustomer, conn);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCustomer;
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
		Customer myCustomer = null;
		Customer newCust = null;
		String sqlStr = "update customer set fname = ?, lname = ? where id = ?";
		ResultSet rs = null;
		Connection conn = null; // has to be outside here, because we want to do a 
		// rollabck in the catch block
		
		String fname = cust.getFirstName();
		String lname = cust.getLastName();
		Long id = cust.getId();

		// programmatic handling on dbc level
		try {
			conn = myConn.getMyConnection();
			// we want to group all transactions to one before we commit
			conn.setAutoCommit(false); 
			// this is a low level
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); 
			PreparedStatement stmt = conn.prepareStatement(sqlStr);
			stmt.setString(1, fname);         
			stmt.setString(2, lname);         
			stmt.setLong(3, id);
			int numOfRows = stmt.executeUpdate();
			if (numOfRows != 1) {
				throw new IllegalArgumentException();
			}
			// if customer has address --> updateAddress, too
			conn.commit(); // last statement in try block is always commit
		} catch (SQLException e) {
			try {
				conn.rollback(); // if it didn't work, we have to rollback
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteCustomer(Customer cust) {
		String sqlStr = "delete from customer where id = ?";
		ResultSet rs = null;
		Connection conn = null; // has to be outside here, because we want to do a 
		// rollabck in the catch block
		
		try {
			conn = myConn.getMyConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			// TODO add the transaction level here
			PreparedStatement stmt = conn.prepareStatement(sqlStr);
			Long custId = cust.getId();
			if (custId != null) {
				stmt.setLong(1, custId);
			}
			int rows = stmt.executeUpdate();
			if (rows != 1) {
				throw new IllegalArgumentException();
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
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
