package com.acn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class MyConnection {

	private DataSource ds;
	private Properties props = new Properties();

	// Constructor - load the properties file, get the Data and set the Connection
	public MyConnection() {
		try {
			
			props.load(new FileReader(new File("properties.props")));

			String url = props.getProperty("url");
			String user = props.getProperty("dbuser");
			String pass = props.getProperty("passwd");
			String driverclass = props.getProperty("driverclass");
			String poolsize = props.getProperty("poolsize");

			BasicDataSource bds = new BasicDataSource();

			bds.setUrl(url);
			bds.setPassword(pass);
			bds.setDriverClassName(driverclass);
			bds.setUsername(user);
			bds.setInitialSize(Integer.parseInt(poolsize));
			ds = bds;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Connection getMyConnection() throws SQLException {
		return ds.getConnection();
	}
}
