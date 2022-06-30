/*
 * package com.aetins.customerportal.web.controllers;
 * 
 * import java.io.FileReader; import java.sql.DriverManager; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.sql.Types;
 * import java.util.Properties;
 * 
 * import org.apache.log4j.Logger;
 * 
 * import com.aetins.customerportal.web.utils.Database; import
 * com.mysql.jdbc.Connection; import com.mysql.jdbc.ResultSetMetaData; import
 * com.mysql.jdbc.Statement;
 * 
 * import oracle.jdbc.OracleCallableStatement; import
 * oracle.jdbc.OracleConnection; import oracle.xdb.XMLType;
 * 
 * public class ExtractDataToXML {
 * 
 * private static final Logger logger = Logger.getLogger(AdminHomeAction.class);
 * 
 * public String getTableData(String tableName) throws Exception { Connection
 * con = null; Statement st = null; ResultSet rs = null; try {
 * 
 * con = getSqlConnection(); System.out.println("connection created"); st =
 * (Statement) con.createStatement(); rs = st.executeQuery("select * from " +
 * tableName); System.out.println("RS created"); ResultSetMetaData rsmd =
 * (ResultSetMetaData) rs.getMetaData(); int colCount = rsmd.getColumnCount();
 * 
 * logger.info("colCount::" + colCount); StringBuilder b = new StringBuilder();
 * 
 * b.append('<').append("?xml version=").append('"').append("1.0").append('"').
 * append(" ").append("encoding=")
 * .append('"').append("windows-1256").append('"').append("?>");
 * System.out.println(b.toString()); b.append("<" + tableName + ">\n");
 * 
 * // StringBuilder b = new StringBuilder("<" + tableName + ">\n");
 * 
 * int num = 1; while (rs.next()) { b.append("<row>"); //
 * b.append("<num>").append(num++).append("</num>"); for (int i = 1; i <=
 * colCount; i++) { String columnName = rsmd.getColumnName(i);
 * b.append('<').append(columnName.toUpperCase()).append('>');
 * b.append(rs.getObject(i));
 * 
 * b.append("</").append(columnName.toUpperCase()).append('>'); }
 * b.append("</row>\n"); } b.append("</" + tableName + ">");
 * 
 * con.close(); return b.toString().replaceAll("&", "&amp;");
 * 
 * // return replaceSpecialChars(b.toString()); } catch (SQLException e) { throw
 * e; } catch (ClassNotFoundException e) { throw e; } finally {
 * 
 * if (rs != null) try { rs.close(); } catch (SQLException e) {
 * e.printStackTrace(); }
 * 
 * if (st != null) try { st.close(); } catch (SQLException e) {
 * e.printStackTrace(); }
 * 
 * if (con != null) try { con.close(); } catch (SQLException e) {
 * e.printStackTrace(); } } }
 * 
 * public static String replaceSpecialChars(String strVar) {
 * 
 * 
 * if (strVar.contains("&")) { strVar = strVar.replace("&", "and"); }
 * 
 * 
 * if (strVar.contains("'")) { strVar = strVar.replace("'", "&apos;"); }
 * 
 * if (strVar.contains("<")) { strVar = strVar.replace("<", "&lt;"); }
 * 
 * if (strVar.contains(">")) { strVar = strVar.replace(">", "&gt;"); }
 * 
 * if (strVar.contains("&")) { strVar = strVar.replace("&", " &amp;"); }
 * 
 * return strVar; }
 * 
 * // Getting jdbc Connection public static Connection getSqlConnection() throws
 * Exception {
 * 
 * Connection con = null; FileReader reader = null; try { reader = new
 * FileReader("C:/ContactsUpdateService/database.properties"); Properties p =
 * new Properties(); p.load(reader); Class.forName("com.mysql.jdbc.Driver"); con
 * = (Connection) DriverManager.getConnection(p.getProperty("database.url"),
 * p.getProperty("database.username"), p.getProperty("database.password"));
 * 
 * logger.info(":::::::: jdbc connection created successfully::::::::::"); }
 * catch (Exception e) { e.getMessage();
 * logger.info("::::::::::: jdbc connection not created :::::::::::: "); }
 * return con; }
 * 
 * public static String synchorniseToISF(String tableName) throws SQLException {
 * 
 * logger.
 * info(":::::Entering inside synchorniseToISF() in ExtractDataToXML Class ");
 * OracleCallableStatement cstmt = null; String output = null; String command =
 * null; try {
 * 
 * // java.sql.Connection conn = getOracleConnection();
 * 
 * Database dataBase = new Database(); OracleConnection conn =
 * (OracleConnection) dataBase.getConnection();
 * logger.info("Connection valuse is " + conn);
 * 
 * ExtractDataToXML de = new ExtractDataToXML(); String xmlData = ""; xmlData =
 * de.getTableData(tableName); logger.info("result::::::::::::::::" + xmlData);
 * 
 * XMLType poXML = XMLType.createXML(conn, xmlData);
 * logger.info("poXML::::::::::::" + poXML.toString());
 * 
 * String table = tableName;
 * 
 * switch (table) {
 * 
 * case "cp_user_info": command =
 * "{call Bpg_Cp_Oracle_Interface.bpc_cp_user_info(?,?)}"; break; case
 * "cp_customer_detail": command =
 * "{call Bpg_Cp_Oracle_Interface.bpc_cp_customer_detail(?,?)}"; break; case
 * "cp_sessionsummary": command =
 * "{call Bpg_Cp_Oracle_Interface.bpc_cp_sessionsummary(?,?)}"; break; case
 * "cp_registration_track": command =
 * "{call Bpg_Cp_Oracle_Interface.bpc_cp_registration_track(?,?)}"; break; case
 * "cp_feedback": command =
 * "{call Bpg_Cp_Oracle_Interface.bpc_cp_feedback(?,?)}"; break;
 * 
 * case "cp_feedback_reply": command =
 * "{call Bpg_Cp_Oracle_Interface.bpc_cp_feedback_reply(?,?)}"; break; }
 * logger.info("::::::: Command is :::::::::" + command); cstmt =
 * (OracleCallableStatement) conn.prepareCall(command); if (cstmt != null) {
 * 
 * cstmt.setObject(1, poXML); cstmt.registerOutParameter(2, Types.VARCHAR);
 * cstmt.execute(); output = cstmt.getString(2); if (cstmt != null) {
 * logger.info(" ::::::::Output :::::::::::: " + cstmt.getString(2)); } } else
 * logger.info("statement is null:::::::::::::::::::::::::::");
 * 
 * } catch (Exception e) { e.printStackTrace();
 * logger.info("Error Inside synchorniseToISF Method :::::::::::" + e); }
 * 
 * return output; }
 * 
 * 
 * public static void main(String args[]) throws Exception {
 * 
 * synchorniseToISF("cp_sessionsummary");
 * 
 * }
 * 
 * 
 * }
 */