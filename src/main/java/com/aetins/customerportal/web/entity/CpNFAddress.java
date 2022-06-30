package com.aetins.customerportal.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This Entity class for Non financial alteration for address details
 * 
 * @author Viswakarthick 28/03/2017
 */
@Entity
@Table(name = "CP_NF_ADDRESS")
public class CpNFAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8483645057876574441L;
	private int nSerialNo;
	private CpRequestMaster nServiceReqNo;
	private String vAddrTypeCode;
	private String vAddrType;
	private BigDecimal vAddrSeqNo;
	private String vAddrLangCode;
	private String vUnitNo;
	private String vAdditionalNo;
	private String vBuildingNo;
	private String vAddr1;
	private String vAddr2;
	private String vAddr3;
	private String vAddr1Old;
	private String vAddr2Old;
	private String vAddr3Old;
	private String vPOBoxNo;
	private String vPOBoxNoOld;
	private String vPostalCode;
	private String vTown;
	private String vState;
	private String vStateOld;
	private String vCountry;
	private String vStateCode;
	private String vStateCodeOld;
	private String vCountryCode;
	private String vCorresAddr;

	// getter
	@Id
	@GeneratedValue
	@Column(name = "N_SERIAL_NO", unique = true, nullable = false, precision = 50, scale = 0)
	public int getnSerialNo() {
		return nSerialNo;
	}

	@ManyToOne
	@JoinColumn(name = "N_SERVICE_REQ_NO", referencedColumnName = "SERVICE_REQUEST_NO", nullable = false)
	public CpRequestMaster getnServiceReqNo() {
		return nServiceReqNo;
	}

	@Column(name = "V_ADDR_TYPE_CODE")
	public String getvAddrTypeCode() {
		return vAddrTypeCode;
	}

	@Column(name = "V_ADDR_TYPE")
	public String getvAddrType() {
		return vAddrType;
	}

	@Column(name = "V_ADDR_SEQ_NO")
	public BigDecimal getvAddrSeqNo() {
		return vAddrSeqNo;
	}

	@Column(name = "V_ADDR_LANG_CODE")
	public String getvAddrLangCode() {
		return vAddrLangCode;
	}

	@Column(name = "V_UNIT_NO")
	public String getvUnitNo() {
		return vUnitNo;
	}

	@Column(name = "V_ADDITIONAL_NO")
	public String getvAdditionalNo() {
		return vAdditionalNo;
	}

	@Column(name = "V_BUILDING_NO")
	public String getvBuildingNo() {
		return vBuildingNo;
	}

	@Column(name = "V_ADDR_1")
	public String getvAddr1() {
		return vAddr1;
	}

	@Column(name = "V_ADDR_2")
	public String getvAddr2() {
		return vAddr2;
	}

	@Column(name = "V_ADDR_3")
	public String getvAddr3() {
		return vAddr3;
	}

	@Column(name = "V_PO_BOX_NO")
	public String getvPOBoxNo() {
		return vPOBoxNo;
	}

	@Column(name = "V_POSTAL_CODE")
	public String getvPostalCode() {
		return vPostalCode;
	}

	@Column(name = "V_TOWN")
	public String getvTown() {
		return vTown;
	}

	@Column(name = "V_STATE")
	public String getvState() {
		return vState;
	}

	@Column(name = "V_COUNTRY")
	public String getvCountry() {
		return vCountry;
	}

	@Column(name = "V_STATE_CODE")
	public String getvStateCode() {
		return vStateCode;
	}

	@Column(name = "V_COUNTRY_CODE")
	public String getvCountryCode() {
		return vCountryCode;
	}

	@Column(name = "V_CORRES_ADDR")
	public String getvCorresAddr() {
		return vCorresAddr;
	}

	@Column(name = "V_ADDR_1_OLD")
	public String getvAddr1Old() {
		return vAddr1Old;
	}

	@Column(name = "V_ADDR_2_OLD")
	public String getvAddr2Old() {
		return vAddr2Old;
	}

	@Column(name = "V_ADDR_3_OLD")
	public String getvAddr3Old() {
		return vAddr3Old;
	}

	@Column(name = "V_PO_BOX_NO_OLD")
	public String getvPOBoxNoOld() {
		return vPOBoxNoOld;
	}

	@Column(name = "V_STATE_CODE_OLD")
	public String getvStateCodeOld() {
		return vStateCodeOld;
	}

	@Column(name = "V_STATE_OLD")
	public String getvStateOld() {
		return vStateOld;
	}

	// setter
	public void setnSerialNo(int nSerialNo) {
		this.nSerialNo = nSerialNo;
	}

	public void setnServiceReqNo(CpRequestMaster nServiceReqNo) {
		this.nServiceReqNo = nServiceReqNo;
	}

	public void setvAddrTypeCode(String vAddrTypeCode) {
		this.vAddrTypeCode = vAddrTypeCode;
	}

	public void setvAddrType(String vAddrType) {
		this.vAddrType = vAddrType;
	}

	public void setvAddrSeqNo(BigDecimal vAddrSeqNo) {
		this.vAddrSeqNo = vAddrSeqNo;
	}

	public void setvUnitNo(String vUnitNo) {
		this.vUnitNo = vUnitNo;
	}

	public void setvAdditionalNo(String vAdditionalNo) {
		this.vAdditionalNo = vAdditionalNo;
	}

	public void setvBuildingNo(String vBuildingNo) {
		this.vBuildingNo = vBuildingNo;
	}

	public void setvAddr1(String vAddr1) {
		this.vAddr1 = vAddr1;
	}

	public void setvAddr2(String vAddr2) {
		this.vAddr2 = vAddr2;
	}

	public void setvAddr3(String vAddr3) {
		this.vAddr3 = vAddr3;
	}

	public void setvPOBoxNo(String vPOBoxNo) {
		this.vPOBoxNo = vPOBoxNo;
	}

	public void setvPostalCode(String vPostalCode) {
		this.vPostalCode = vPostalCode;
	}

	public void setvTown(String vTown) {
		this.vTown = vTown;
	}

	public void setvState(String vState) {
		this.vState = vState;
	}

	public void setvCountry(String vCountry) {
		this.vCountry = vCountry;
	}

	public void setvStateCode(String vStateCode) {
		this.vStateCode = vStateCode;
	}

	public void setvCountryCode(String vCountryCode) {
		this.vCountryCode = vCountryCode;
	}

	public void setvCorresAddr(String vCorresAddr) {
		this.vCorresAddr = vCorresAddr;
	}

	public void setvAddrLangCode(String vAddrLangCode) {
		this.vAddrLangCode = vAddrLangCode;
	}

	public void setvAddr1Old(String vAddr1Old) {
		this.vAddr1Old = vAddr1Old;
	}

	public void setvAddr2Old(String vAddr2Old) {
		this.vAddr2Old = vAddr2Old;
	}

	public void setvAddr3Old(String vAddr3Old) {
		this.vAddr3Old = vAddr3Old;
	}

	public void setvStateOld(String vStateOld) {
		this.vStateOld = vStateOld;
	}

	public void setvStateCodeOld(String vStateCodeOld) {
		this.vStateCodeOld = vStateCodeOld;
	}

	public void setvPOBoxNoOld(String vPOBoxNoOld) {
		this.vPOBoxNoOld = vPOBoxNoOld;
	}

}
