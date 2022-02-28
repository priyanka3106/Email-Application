package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	public Connection getConnection() throws SQLException
	{
		Connection conn=null;
		String databaseUrl="jdbc:mysql://localhost:3306/emaildb";
		String userName="root";
		String userPassword="mysql";
		conn=DriverManager.getConnection(databaseUrl, userName, userPassword);
		return conn;
		
	}
}



