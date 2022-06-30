
package com.aetins.customerportal.web.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;

public class OracleDatabase extends DBConfig_ {


	private OracleDatabase() throws Exception {
		Context initContext = null;
		try {
			ods = new OracleDataSource();
			ods.setURL(url);
			ods.setUser(username);
			ods.setPassword(password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public OracleDatabase(String url, String userName, String password) throws SQLException{
		ocpds = new OracleConnectionPoolDataSource();
		ocpds.setURL(url);
		ocpds.setUser(username);
		ocpds.setPassword(password);
		
		ods = new OracleDataSource();
		ods.setURL(url);
		ods.setUser(username);
		ods.setPassword(password);
	}

	public Connection getConnection() throws SQLException {
		return ods.getConnection();
	}
	
	public Connection getPooledConnection() throws SQLException {
		PooledConnection pc = ocpds.getPooledConnection();

		return pc.getConnection();
	}

	public void resetConnection(){
		try {
			if(ods != null)
				((Connection) ods).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			_instance = null;
		}
	}
	
	private static OracleDatabase _instance = null;

	protected static Database createInstance() throws Exception {
		if (_instance == null) {
			_instance = new OracleDatabase();
		}
		return _instance;
	}

	private static OracleDataSource ods;
	private static OracleConnectionPoolDataSource ocpds = null;
}