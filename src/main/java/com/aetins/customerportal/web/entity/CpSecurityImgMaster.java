package com.aetins.customerportal.web.entity;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author satendra
 *
 */
@Entity
@Table(name = "CP_SECURITYIMG_MASTER")
@ManagedBean(name = "CpSecurityImgMaster")

public class CpSecurityImgMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "N_ID", unique = true, nullable = false, precision = 15, scale = 0)
    private int nId;

    @Column(name = "V_IMG_PATH")
    private String vImgPath;

    @Column(name = "V_IMG_TYPE")
    private String vImgType;

    @Column(name = "V_DESC")
    private String vDesc;

    public int getnId() {
	return nId;
    }

    public void setnId(int nId) {
	this.nId = nId;
    }

    public String getvImgPath() {
	return vImgPath;
    }

    public void setvImgPath(String vImgPath) {
	this.vImgPath = vImgPath;
    }

    public String getvImgType() {
	return vImgType;
    }

    public void setvImgType(String vImgType) {
	this.vImgType = vImgType;
    }

    public String getvDesc() {
	return vDesc;
    }

    public void setvDesc(String vDesc) {
	this.vDesc = vDesc;
    }

}
