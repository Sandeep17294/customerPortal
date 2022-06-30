package com.aetins.customerportal.web.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "CP_USER_INFO")

public class CpUserInfo implements java.io.Serializable {

	private static final long serialVersionUID = -8160952900135226001L;

	private int nid;

	private int ncustRefNo;

	private String vgroup;

	private String vtitle;

	private String vuserName;

	private String vprefName;

	private Date ddob;

	private String vpassword;

	private String vpwMustChange;

	/* private Blob vimage; */
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

	private int nImageId;

	private String vBusrDept;

	private String vToken;

	private String vLockedTime;
	
	private String secretToken;
	
	private boolean isUsing2FA;
	
    private Collection<Role> roles;
    
    private VerificationToken verificationToken;

	// getter
	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "N_ID", unique = true, nullable = false, precision = 15, scale = 0)
	public int getNid() {
		return nid;
	}

	@Column(name = "V_GROUP")

	public String getVgroup() {
		return vgroup;
	}

	@Column(name = "V_Title")
	public String getVtitle() {
		return vtitle;
	}

	@Column(name = "V_USER_NAME")
	public String getVuserName() {
		return vuserName;
	}

	@Column(name = "V_PREF_NAME")
	public String getVprefName() {
		return vprefName;
	}

	@Column(name = "D_DOB", columnDefinition = "DATETIME")
	//@Temporal(TemporalType.TIMESTAMP)
	public Date getDdob() {
		return ddob;
	}

	@Column(name = "V_PASSWORD")
	public String getVpassword() {
		return vpassword;
	}

	@Column(name = "V_PW_MUST_CHANGE")
	public String getVpwMustChange() {
		return vpwMustChange;
	}

	@Column(name = "V_EMAIL")
	public String getVemail() {
		return vemail;
	}

	@Column(name = "V_QUESTION_1")
	public String getVquestion1() {
		return vquestion1;
	}

	@Column(name = "V_ANSWER_1")
	public String getVanswer1() {
		return vanswer1;
	}

	@Column(name = "V_QUESTION_2")
	public String getVquestion2() {
		return vquestion2;
	}

	@Column(name = "V_ANSWER_2")
	public String getVanswer2() {
		return vanswer2;
	}

	@Column(name = "V_PREF_LANG")
	public String getVpreflang() {
		return vpreflang;
	}

	@Column(name = "V_CONTACT_NO")
	public String getVcontactNo() {
		return vcontactNo;
	}

	@Column(name = "V_ACTIVE")
	public String getVactive() {
		return vactive;
	}

	@Column(name = "V_LOCKED")
	public String getVlocked() {
		return vlocked;
	}

	@Column(name = "V_ACTIVE_LOGIN")
	public String getVactiveLogin() {
		return vactiveLogin;
	}

	@Column(name = "V_LOGIN_SESSIONID")
	public String getVloginSession() {
		return vloginSession;
	}

	@Column(name = "V_LASTUPD_USER")
	public String getVlastupdUser() {
		return vlastupdUser;
	}

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "N_ID"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Column(name = "N_CUST_REF_NO")
	public int getNcustRefNo() {
		return ncustRefNo;
	}

	@Column(name = "V_LASTUPD_DATE", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getVlastupdDate() {
		return vlastupdDate;
	}

	@Column(name = "V_USER_ACTIVATION_DATE", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getVuserActivationDate() {
		return vuserActivationDate;
	}

	@Column(name = "N_IMAGE_ID")
	public int getnImageId() {
		return nImageId;
	}

	@Column(name = "V_BUSR_DEP")
	public String getvBusrDept() {
		return vBusrDept;
	}

	@Column(name = "V_TOKEN")
	public String getvToken() {
		return vToken;
	}

	@Column(name = "V_LOCKED_TIME")
	public String getvLockedTime() {
		return vLockedTime;
	}

	@OneToOne(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public VerificationToken getVerificationToken() {
		return verificationToken;
	}
	
	public String getSecretToken() {
		return secretToken;
	}

	public void setSecretToken(String secretToken) {
		this.secretToken = secretToken;
	}

	public boolean isUsing2FA() {
		return isUsing2FA;
	}

	public void setUsing2FA(boolean isUsing2FA) {
		this.isUsing2FA = isUsing2FA;
	}

	// setter
	public void setNid(int nid) {
		this.nid = nid;
	}

	public void setVgroup(String vgroup) {
		this.vgroup = vgroup;
	}

	public void setVtitle(String vtitle) {
		this.vtitle = vtitle;
	}

	public void setVuserName(String vuserName) {
		this.vuserName = vuserName;
	}

	public void setVprefName(String vprefName) {
		this.vprefName = vprefName;
	}

	public void setVpassword(String vpassword) {
		this.vpassword = vpassword;
	}

	public void setVpwMustChange(String vpwMustChange) {
		this.vpwMustChange = vpwMustChange;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public void setVquestion1(String vquestion1) {
		this.vquestion1 = vquestion1;
	}

	public void setVanswer1(String vanswer1) {
		this.vanswer1 = vanswer1;
	}

	public void setVquestion2(String vquestion2) {
		this.vquestion2 = vquestion2;
	}

	public void setVanswer2(String vanswer2) {
		this.vanswer2 = vanswer2;
	}

	public void setVpreflang(String vpreflang) {
		this.vpreflang = vpreflang;
	}

	public void setVcontactNo(String vcontactNo) {
		this.vcontactNo = vcontactNo;
	}

	public void setVactive(String vactive) {
		this.vactive = vactive;
	}

	public void setVlocked(String vlocked) {
		this.vlocked = vlocked;
	}

	public void setVactiveLogin(String vactiveLogin) {
		this.vactiveLogin = vactiveLogin;
	}

	public void setVloginSession(String vloginSession) {
		this.vloginSession = vloginSession;
	}

	public void setVlastupdUser(String vlastupdUser) {
		this.vlastupdUser = vlastupdUser;
	}

	public void setnImageId(int nImageId) {
		this.nImageId = nImageId;
	}

	public void setDdob(Date ddob) {
		this.ddob = ddob;
	}

	public void setNcustRefNo(int ncustRefNo) {
		this.ncustRefNo = ncustRefNo;
	}

	public void setVlastupdDate(Date vlastupdDate) {
		this.vlastupdDate = vlastupdDate;
	}

	public void setVuserActivationDate(Date vuserActivationDate) {
		this.vuserActivationDate = vuserActivationDate;
	}

	public void setvBusrDept(String vBusrDept) {
		this.vBusrDept = vBusrDept;
	}

	public void setvToken(String vToken) {
		this.vToken = vToken;
	}

	public void setvLockedTime(String vLockedTime) {
		this.vLockedTime = vLockedTime;
	}

	

	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}
	

}