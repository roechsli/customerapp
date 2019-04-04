package com.acn;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMyConnection {
	MyConnection myconn;

	@Before
	public void setUp() throws Exception {
		myconn = new MyConnection();
	}

	@After
	public void tearDown() throws Exception {
		myconn = null;
	}

	@Test
	public void testGetMyConnection() throws SQLException, FileNotFoundException, IOException {
		assertNotNull(myconn);
		MyConnection conn = myconn.getMyConnection();
		assertNotNull(conn);
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseMajorVersion());
		System.out.println(conn.getMetaData().getDriverName());

		conn.close();
	}

	@Test
	public void testReadCustomer() throws SQLException, FileNotFoundException, IOException {
		String sql = "select * from customer";
		Statement stm = null;
		ResultSet rs = null;
		assertNotNull(myconn);
		Connection conn = myconn.getMyConnection();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getLong("id")+" "+rs.getString("fname")+" " + rs.getString("lname"));
		}
		assertNotNull(conn);

		conn.close();
	}

}
