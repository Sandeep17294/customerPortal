package com.aetins.customerportal.web.utils;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import oracle.jdbc.pool.OracleDataSource;

public abstract class DBConfig_ extends Database{
	private static final Log log = LogFactory.getLog(DBConfig_.class);
	//private static String fileName = "C:\\database.properties";
	
	private static String hostName = null;
	private static String serviceName = null;
	private static String port = null;
	
	
	public static String getUserName() {
		return username;
	}

	public static void setUserName(String userName) {
		Database.username = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Database.password = password;
	}

	public static String getHostName() {
		return hostName;
	}

	public static void setHostName(String hostName) {
		DBConfig_.hostName = hostName;
	}

	public static String getServiceName() {
		return serviceName;
	}

	public static void setServiceName(String serviceName) {
		DBConfig_.serviceName = serviceName;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		DBConfig_.port = port;
	}
	
	public static String getUrl() {
		return url;
	}

	public static void setUrl(String p_HostName, String p_ServiceName, String p_port) {
		Database.url = "jdbc:oracle:thin:@" +p_HostName+ ":" +p_port+ ":" + p_ServiceName;
	}

	/*public static void loadProperties() {
		
		try {
			init();
			String [] token = null;
			if(BSLUtils.isNotNull(url)){
				token = url.split(":");
			}
			hostName = token[3].replace("@","");
			serviceName = token[5];
			port = token[4];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public static void alterProperties(String p_UserName, String p_Password, String p_HostName, String p_ServiceName, String p_port) {

		Properties newProps = new Properties();
		Enumeration enumProps = p.propertyNames();
		String key = "";

		while ( enumProps.hasMoreElements() ) {

			key = (String) enumProps.nextElement();

				if (key.equals("database.url")) {
					newProps.setProperty(key, "jdbc:oracle:thin:@" +p_HostName+ ":" +p_port+ ":" + p_ServiceName);
				} else if (key.equals("database.username")) {
					newProps.setProperty(key, p_UserName);
				} else if (key.equals("database.password")) {
					newProps.setProperty(key, p_Password);
				}else {
					newProps.setProperty(key, p.getProperty(key));
				}
		}
		p = newProps;
	}
	
	public static void saveProperties() {
		OutputStream os;
		try {
			os = new FileOutputStream(fileName);
			p.store(os, "Properties File to the Test Application");
			os.close();
		} catch (IOException ioe) {
			log.error("I/O Exception.");
			ioe.printStackTrace();
			System.exit(0);
		}
	}
	
	private static OracleDataSource odsTest;
	public static Connection connectionTest(){
		Connection conn = null;
		try {
			odsTest = new OracleDataSource();
			odsTest.setURL(url);
			odsTest.setUser(username);
			odsTest.setPassword(password);
			conn = odsTest.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnectionTest() throws IOException{
		if(odsTest != null)
		((Closeable) odsTest).close();
	}
	
	/*public static void main(String[] args) {
		DBConfig_.loadProperties();
		//log.info(DBConfig.getPassword());
	}*/

}
