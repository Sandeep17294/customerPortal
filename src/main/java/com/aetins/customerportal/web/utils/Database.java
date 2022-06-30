package com.aetins.customerportal.web.utils;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.driver.OracleConnection;

//import org.apache.commons.dbcp.PoolableConnection;
import org.apache.log4j.Logger;

public class Database {
	static Logger logger = Logger.getLogger(Database.class);
	static Connection con = null;
	private static DataSource dataSource = null;
	private static Database db = new Database();
	private static OracleDatabase oradb = null;

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {

			/*
			 * String fileName = Database.fileName; // String fileName =
			 * Executions.getCurrent().getDesktop().getWebApp().getRealPath(
			 * "/WEB-INF/database.properties");
			 * logger.info(",,,,,,,,"+fileName); conn =
			 * Database.getInstance().getConnection(); if (conn != null) {
			 * logger.info("Successfully connected"); DatabaseMetaData meta =
			 * conn.getMetaData(); logger.info("\nDriver Information");
			 * logger.info("Driver Name: " + meta.getDriverName()); logger.info(
			 * "Driver Version: " + meta.getDriverVersion()); logger.info(
			 * "\nDatabase Information "); logger.info("Database Name: " +
			 * meta.getDatabaseProductName()); logger.info("Database Version: "
			 * + meta.getDatabaseProductVersion()); }
			 */
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * Created By : Mathi Created On : 09 Oct 2013 Description : Converts the
	 * connection to OracleConnection used for Oracle Object calls.
	 */
	/*
	 * private Connection getDelegateConnection(Connection conn){
	 * org.apache.commons.dbcp.DelegatingConnection delegatingConnection =
	 * (org.apache.commons.dbcp.DelegatingConnection)conn; PoolableConnection
	 * poolableConnection = (PoolableConnection)
	 * delegatingConnection.getDelegate(); Connection oracleConnection1 =
	 * poolableConnection.getDelegate(); logger.info("Driver = " +
	 * delegatingConnection.getClass().getName()); logger.info("Driver = " +
	 * oracleConnection1.getClass().getName()); return (OracleConnection)
	 * oracleConnection1; }
	 */

	/**
	 * Created By : Mathi Created On : 09 Oct 2013 Description : Gets the
	 * delegate connection for Oracle Objects
	 */
	public Connection getDelegateConnection() throws Exception {
		if (oradb == null)
			oradb = new OracleDatabase(url, username, password);
		// oradb = new OracleDatabase("jdbc:oracle:thin:@psdbsrv001:1521:devd0",
		// "custptld", "custptld");
		return oradb.getConnection();
		// return getDelegateConnection(getConnection());
	}

	/*
	 * Created By : Mathi Created on : 15 Oct 2012 Description : Close the
	 * connection after executing callable statement and prepared statement.
	 */
	public static void closeConnection() {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initDataSource() {
		try {
			logger.info("Inside initDataSource");
			InitialContext context = new InitialContext();
			logger.info("The datasource is ...$$$$$$$$$$$$$$$$$$$");
			init();
			// dataSource = (DataSource)
			// context.lookup("cell/clusters/UPRT01SA/jdbc/aetinsDataSource");
			logger.info("JNDI NAme = " + name);
			dataSource = (DataSource) context.lookup(name);
			logger.info("The datasource is created...$$%^@#$@#$@#$@#$@$%^#^&$&*$@#%@$%@$%@%");
		} catch (Exception e) {
			logger.info("Data Source Error ===================== " + e);
			e.printStackTrace();
		}
	}

	/*
	 * Created By : Mathi Created on : 15 Oct 2012 Description : Initialize the
	 * connection if the connection object is null and return the connection.
	 */
	private static Connection initConnection() {
		logger.info("Inside initConnection");
		/*
		 * if(BSLUtils.isNull(dataSource)) initDataSource();
		 */

		logger.info("Datasource Created..........................................." + dataSource);
		try {
			init();
			if (oradb == null)
				oradb = new OracleDatabase(url, username, password);
			// oradb = new
			// OracleDatabase("jdbc:oracle:thin:@psdbsrv001:1521:devd0",
			// "custptld", "custptld");
			con = oradb.getPooledConnection();
			if (con == null) {
				con = dataSource.getConnection();
			} else {
				if (con.isClosed()) {
					System.out.println("***connection closed and opening new connection***");
					con = dataSource.getConnection();
				}
			}

			logger.info("The data connection is created...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + con);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select sysdate from dual");
			if (rs.next())
				logger.info("Current Time = " + rs.getDate(1));
			else
				logger.info("Today = " + new Date());
		} catch (Exception e) {
			logger.info("The data Connection error ......############################" + e);
			e.printStackTrace();
		}
		return con;
	}

	/*
	 * Created By : Mathi Created on : 15 Oct 2012 Description : Excecutes a
	 * query and returns the result set. Returns null if there is any exception.
	 */
	public static ResultSet executeQuery(String query) throws SQLException {
		ResultSet rs = null;
		try {
			con = initConnection();
			Statement st = con.createStatement();
			logger.info("Statement = " + st);
			rs = st.executeQuery(query);
			logger.info("Query executed");
		} catch (Exception e) {
			logger.info("An Error Occurred while executing the query - " + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}

	/*
	 * Created By : Hemamalini Created on : 14 March 2013 Description :
	 * Excecutes a query and returns the result set. Returns null if there is
	 * any exception.
	 */
	public static ResultSet executeQueryPS(String query) throws SQLException {
		ResultSet rs = null;
		try {
			con = initConnection();
			PreparedStatement statement;
			statement = con.prepareStatement(query);
			rs = statement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/*
	 * Created By : Mathi Created on : 15 Oct 2012 Description : Runs an update
	 * query and returns the result.
	 */
	public static int executeUpdate(String query) throws SQLException {
		int result = 0;
		try {
			con = initConnection();
			Statement st = con.createStatement();
			result = st.executeUpdate(query);
			con.commit();
			con.close();
		} catch (Exception e) {
			if (con != null)
				con.close();
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Created By : Mathi Created on : 15 Oct 2012 Description : Creates a
	 * callable statement used to call a procedure.
	 */
	public static CallableStatement getCallableStatement(String query) {
		CallableStatement cStmt = null;
		try {
			con = initConnection();
			cStmt = con.prepareCall(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cStmt;
	}

	/*
	 * Created By : Vikas Created on : 16 August 2016 Description : Creates a
	 * callable statement used to call a procedure and commits.
	 */
	public static CallableStatement getCallableStatementCommit(String query) {
		CallableStatement cStmt = null;
		try {
			con = initConnection();
			cStmt = con.prepareCall(query);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cStmt;
	}

	public static Database getInstance() throws Exception {
		logger.info("Inside getInstanceMethod");
		logger.info("DB Created");
		return db;
		/*
		 * init(); return OracleDatabase.createInstance();
		 */
	}

	protected static void init() throws Exception {
		p = LoadProperties.loadParams("bundle/database");
		InputStream is = null;
		try {
			
			logger.info(p);

			VENDOR = p.getProperty("database.vendor");
			MODE = p.getProperty("database.mode");
			name = p.getProperty("database.jndi.ds.name");
			driver = p.getProperty("database.driver");
			url = p.getProperty("database.url");
			username = p.getProperty("database.username");
			password = p.getProperty("database.password");
			
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public static String VENDOR = null;
	public static String MODE = null;

	public Connection getConnection() throws Exception {
		return initConnection();
	}

	public boolean isJNDIDataSource;
	public DataSource ds;
	protected static Properties p = null;

	public static String name;
	public static String driver;
	public static String url;
	public static String dbServerIP;
	public static String dbServerPortNo;
	public static String dbServerInstance;
	public static String username;
	protected static String password;

	public static final String ORACLE = "ORACLE";
	public static final String DEVELOPMENT = "DEVELOPMENT";
	public static final String TEST = "TEST";
	public static final String PRODUCTION = "PRODUCTION";

	// protected static String fileName =
	// "C:/platform/ZKProjects/CP_SABB_DEV/WebContent/WEB-INF/database.properties";
	protected static String fileName = "D:/TOMCAT/webapps/CustomerPortal/WEB-INF/database.properties";
	// protected static String fileName =
	// Executions.getCurrent().getDesktop().getWebApp().getRealPath("/WEB-INF/database.properties");
	// C:\platform\ZKProjects\CP_SABB_DEV\WebContent\WEB-INF
}
