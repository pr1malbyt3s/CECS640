package edu.louisville.cecs640.controllers;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UsersTableAccess {
	private String dbName = null;
	private String userID = null;
	private String password = null;
	private String host = null;
	private String url = null;
	private String driver = null;
	private String crypt = null;
	private Connection dbConnection = null;
	
	public void GetConnectionProperties(String fileName) {
		FileInputStream f = null;
		Properties p = null;
		try {
			f = new FileInputStream(fileName);
			p = new Properties();
			p.load(f);
			userID = p.getProperty("userID");
			password = p.getProperty("password");
			host = p.getProperty("host");
			driver = p.getProperty("driver");
			dbName = p.getProperty("dbName");
			crypt = p.getProperty("crypt");
		} catch(Exception e) {
			System.out.println("GetConnectionProperties Error.");
			e.printStackTrace();
		}
		
	}

	public void DatabaseConnect() {
		url = host + dbName;
		try {
			Class.forName(driver);
		}
		catch (Exception e) {
			System.out.println("DatabaseConnect Error.");
		}
		try {
			dbConnection = DriverManager.getConnection(url, userID, password);
		}
		catch (SQLException sqle) {
			System.out.println("SQL Connect Error.");
		}
	}
	
	public void DatabaseDisconnect() {
		try {
			dbConnection.close();
		}
		catch (Exception sqle) {
			System.out.println("SQL Disconnect Error.");
		}
	}
	
	public boolean ValidateUser(String user, String password) throws SQLException {
		boolean validate = false;
		String query = "SELECT * FROM USERS WHERE UPPER(NAME) = UPPER(?) AND DECRYPT_CHAR(PASSWORD, ?) = ? ";
		PreparedStatement ps = dbConnection.prepareStatement( query );
		ps.setString(1, user);
		ps.setString(2, crypt);
		ps.setString(3, password);
		try {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				validate = true;
			}
		} 
		catch (SQLException e) {
			System.out.println("SQL Execute Error.");
		}
		return validate;
	}
}
