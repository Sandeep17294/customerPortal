package com.aetins.customerportal.web.dto;

import java.sql.Blob;
import java.util.Collection;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import com.aetins.customerportal.web.entity.Privilege;
import com.aetins.customerportal.web.entity.Role;

@ManagedBean(name = "cpUserInfoDTO", eager = true)
public class CpUserInfoDTO {
	private int nid;
	private int ncustRefNo;
	private String vgroup;
	private String vtitle;
	private String vuserName;
	private String vprefName;
	private Date ddob;
	private String vpassword;
	private String vpwMustChange;
	// private Blob vimage;
	private String vemail;
	private String vquestion1;
	private String vanswer1;
	private String vquestion2;
	private String vanswer2;
	private String vpreflang;
	private String vcontactNo;
	private String vactive;
	private String vlocked;
	private Date vuserActivationDate;
	private String vactiveLogin;
	private String vloginSession;
	private String vlastupdUser;
	private Date vlastupdDate;
	private String vLockedTime;
	private Collection<Role> roles;

	// temp image id
	private int nImageId;

	// Added for search date Modifiation
	private String registrationDate;

	// setter and getter

	// setter and getter

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getVgroup() {
		return vgroup;
	}

	public void setVgroup(String vgroup) {
		this.vgroup = vgroup;
	}

	public String getVtitle() {
		return vtitle;
	}

	public void setVtitle(String vtitle) {
		this.vtitle = vtitle;
	}

	public String getVuserName() {
		return vuserName;
	}

	public void setVuserName(String vuserName) {
		this.vuserName = vuserName;
	}

	public String getVprefName() {
		return vprefName;
	}

	public void setVprefName(String vprefName) {
		this.vprefName = vprefName;
	}

	public String getVpassword() {
		return vpassword;
	}

	public void setVpassword(String vpassword) {
		this.vpassword = vpassword;
	}

	public String getVpwMustChange() {
		return vpwMustChange;
	}

	public void setVpwMustChange(String vpwMustChange) {
		this.vpwMustChange = vpwMustChange;
	}

	/*
	 * public Blob getVimage() { return vimage; }
	 * 
	 * public void setVimage(Blob vimage) { this.vimage = vimage; }
	 * 
	 * public Blob getVimage() { return vimage; } public void setVimage(Blob
	 * vimage) { this.vimage = vimage; }
	 */

	// public Blob getVimage() {
	// return vimage;
	// }
	// public void setVimage(Blob vimage) {
	// this.vimage = vimage;
	// }

	public String getVemail() {
		return vemail;
	}

	public String getvLockedTime() {
		return vLockedTime;
	}

	public void setvLockedTime(String vLockedTime) {
		this.vLockedTime = vLockedTime;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public String getVquestion1() {
		return vquestion1;
	}

	public void setVquestion1(String vquestion1) {
		this.vquestion1 = vquestion1;
	}

	public String getVanswer1() {
		return vanswer1;
	}

	public void setVanswer1(String vanswer1) {
		this.vanswer1 = vanswer1;
	}

	public String getVquestion2() {
		return vquestion2;
	}

	public void setVquestion2(String vquestion2) {
		this.vquestion2 = vquestion2;
	}

	public String getVanswer2() {
		return vanswer2;
	}

	public void setVanswer2(String vanswer2) {
		this.vanswer2 = vanswer2;
	}

	public String getVpreflang() {
		return vpreflang;
	}

	public void setVpreflang(String vpreflang) {
		this.vpreflang = vpreflang;
	}

	public String getVcontactNo() {
		return vcontactNo;
	}

	public void setVcontactNo(String vcontactNo) {
		this.vcontactNo = vcontactNo;
	}

	public String getVactive() {
		return vactive;
	}

	public void setVactive(String vactive) {
		this.vactive = vactive;
	}

	public String getVlocked() {
		return vlocked;
	}

	public void setVlocked(String vlocked) {
		this.vlocked = vlocked;
	}

	public String getVloginSession() {
		return vloginSession;
	}

	public void setVloginSession(String vloginSession) {
		this.vloginSession = vloginSession;
	}

	public String getVlastupdUser() {
		return vlastupdUser;
	}

	public void setVlastupdUser(String vlastupdUser) {
		this.vlastupdUser = vlastupdUser;
	}

	public int getNcustRefNo() {
		return ncustRefNo;
	}

	public void setNcustRefNo(int ncustRefNo) {
		this.ncustRefNo = ncustRefNo;
	}

	public Date getVlastupdDate() {
		return vlastupdDate;
	}

	public void setVlastupdDate(Date vlastupdDate) {
		this.vlastupdDate = vlastupdDate;
	}

	public String getVactiveLogin() {
		return vactiveLogin;
	}

	public void setVactiveLogin(String vactiveLogin) {
		this.vactiveLogin = vactiveLogin;
	}

	public Date getVuserActivationDate() {
		return vuserActivationDate;
	}

	public void setVuserActivationDate(Date vuserActivationDate) {
		this.vuserActivationDate = vuserActivationDate;
	}

	// temp image id
	public int getnImageId() {
		return nImageId;
	}

	public void setnImageId(int nImageId) {
		this.nImageId = nImageId;
	}

	public Date getDdob() {
		return ddob;
	}

	public void setDdob(Date ddob) {
		this.ddob = ddob;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "CpUserInfoDTO [nid=" + nid + ", ncustRefNo=" + ncustRefNo + ", vgroup=" + vgroup + ", vtitle=" + vtitle
				+ ", vuserName=" + vuserName + ", vprefName=" + vprefName + ", ddob=" + ddob + ", vpassword="
				+ vpassword + ", vpwMustChange=" + vpwMustChange + ", vemail=" + vemail + ", vquestion1=" + vquestion1
				+ ", vanswer1=" + vanswer1 + ", vquestion2=" + vquestion2 + ", vanswer2=" + vanswer2 + ", vpreflang="
				+ vpreflang + ", vcontactNo=" + vcontactNo + ", vactive=" + vactive + ", vlocked=" + vlocked
				+ ", vuserActivationDate=" + vuserActivationDate + ", vactiveLogin=" + vactiveLogin + ", vloginSession="
				+ vloginSession + ", vlastupdUser=" + vlastupdUser + ", vlastupdDate=" + vlastupdDate + ", vLockedTime="
				+ vLockedTime + ", roles=" + roles + ", nImageId=" + nImageId + ", registrationDate=" + registrationDate
				+ "]";
	}

	

}