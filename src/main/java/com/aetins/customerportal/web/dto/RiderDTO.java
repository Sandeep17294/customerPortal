package com.aetins.customerportal.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class RiderDTO {
	
private int serial_No;
private ServiceRequestMasterDTO serv_Request_No;
private String  rider_Code;
private String rider_Name;
private BigDecimal current_Term;
private BigDecimal old_Term;
private BigDecimal current_SA;
private BigDecimal old_SA;
private String transaction_Type;
private Date startDate;
private Date endDate;

public Date getStartDate() {
	return startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
private boolean status;


public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public int getSerial_No() {
	return serial_No;
}
public ServiceRequestMasterDTO getServ_Request_No() {
	return serv_Request_No;
}
public String getRider_Code() {
	return rider_Code;
}
public String getRider_Name() {
	return rider_Name;
}
public BigDecimal getCurrent_Term() {
	return current_Term;
}

public BigDecimal getCurrent_SA() {
	return current_SA;
}

public String getTransaction_Type() {
	return transaction_Type;
}
public void setSerial_No(int serial_No) {
	this.serial_No = serial_No;
}
public void setServ_Request_No(ServiceRequestMasterDTO requestNo) {
	this.serv_Request_No = requestNo;
}
public void setRider_Code(String rider_Code) {
	this.rider_Code = rider_Code;
}
public void setRider_Name(String rider_Name) {
	this.rider_Name = rider_Name;
}
public void setCurrent_Term(BigDecimal bigDecimal) {
	this.current_Term = bigDecimal;
}

public void setCurrent_SA(BigDecimal bigDecimal) {
	this.current_SA = bigDecimal;
}

public void setTransaction_Type(String transaction_Type) {
	this.transaction_Type = transaction_Type;
}
public BigDecimal getOld_Term() {
	return old_Term;
}
public BigDecimal getOld_SA() {
	return old_SA;
}
public void setOld_Term(BigDecimal old_Term) {
	this.old_Term = old_Term;
}
public void setOld_SA(BigDecimal old_SA) {
	this.old_SA = old_SA;
}



}
