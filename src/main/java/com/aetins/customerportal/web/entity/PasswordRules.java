package com.aetins.customerportal.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CP_SETTING")
public class PasswordRules implements java.io.Serializable{
	
	private static final long serialVersionUID = -8160952900135226001L;
	
	private int nSettingId;
	private int nPasswordLength;
	private String vSpecialCharAllow;
	private int nMinAlphabetAllow;
	private int nMinNoAllow;
	private int nMinSpecialAllow;
	private int nMinLowerAllow;
	private int nMinUpperAllow;
	private String vCompPrefix;
	private String vSmtpHost;
	private String vSmtpPort;
	private String vSmtpUser;
	private String vSmtpPassword;
	private String vFromEmail;
	private String vCsdEmail;
	private int nPasswordAge;
	private int nActivationPeriod;
	private int nDormantPeriod;
	private String vPasswordSameasUser;
	private int nPasswordHis;
	private String vChangePassReq;
	private int nAccntLockout;
	private String vReleaseAccntLock;
	private int nTimeReleaseAccntLock;
	private int nRequiredCaptcha;
	private String vTncEng;
	private String vTncArb;
	private int nRequiredMobOTP;
	private int nQuestionCount;
	
	@Id	
	@GeneratedValue
	@Column(name = "N_SETTINGID")
	public int getnSettingId() {
		return nSettingId;
	}
	
	@Column(name = "N_PASSWORD_LENGTH")
	public int getnPasswordLength() {
		return nPasswordLength;
	}
	
	@Column(name = "V_SPECIAL_CHARACTER_ALLOWED")
	public String getvSpecialCharAllow() {
		return vSpecialCharAllow;
	}
	
	@Column(name = "N_MIN_ALPHABETS_ALLOWED")
	public int getnMinAlphabetAllow() {
		return nMinAlphabetAllow;
	}
	
	@Column(name = "N_MIN_NUMBERS_ALLOWED")
	public int getnMinNoAllow() {
		return nMinNoAllow;
	}
	
	@Column(name = "N_MIN_SPECIAL_ALLOWED")
	public int getnMinSpecialAllow() {
		return nMinSpecialAllow;
	}
	
	@Column(name = "N_MIN_LOWERCASE_ALLOWED")
	public int getnMinLowerAllow() {
		return nMinLowerAllow;
	}
	
	@Column(name = "N_MIN_UPPERCASE_ALLOWED")
	public int getnMinUpperAllow() {
		return nMinUpperAllow;
	}
		
	@Column(name = "V_COMP_PREFIX")
	public String getvCompPrefix() {
		return vCompPrefix;
	}
	@Column(name = "V_SMTPHOST")
	public String getvSmtpHost() {
		return vSmtpHost;
	}
	
	@Column(name = "V_SMTPPORT")
	public String getvSmtpPort() {
		return vSmtpPort;
	}
	
	@Column(name = "V_SMTPUSER")
	public String getvSmtpUser() {
		return vSmtpUser;
	}
	
	@Column(name = "V_SMTPPASSWORD")
	public String getvSmtpPassword() {
		return vSmtpPassword;
	}
	
	@Column(name = "V_FROMEMAIL")
	public String getvFromEmail() {
		return vFromEmail;
	}
	
	
	@Column(name = "V_CSDEMAIL")
	public String getvCsdEmail() {
		return vCsdEmail;
	}
		
	@Column(name = "V_TNCENG")
	public String getvTncEng() {
		return vTncEng;
	}
	@Column(name = "V_TNCARB")
	public String getvTncArb() {
		return vTncArb;
	}
	@Column(name = "N_QUESTION_COUNT")
	public int getnQuestionCount() {
		return nQuestionCount;
	}

	public void setnQuestionCount(int nQuestionCount) {
		this.nQuestionCount = nQuestionCount;
	}

	public void setvTncEng(String vTncEng) {
		this.vTncEng = vTncEng;
	}

	public void setvTncArb(String vTncArb) {
		this.vTncArb = vTncArb;
	}

	
	@Column(name = "N_PASSWORD_AGE")
	public int getnPasswordAge() {
		return nPasswordAge;
	}
	@Column(name = "N_ACTIVATION_PERIOD")
	public int getnActivationPeriod() {
		return nActivationPeriod;
	}
	@Column(name = "N_DORMANT_PERIOD")
	public int getnDormantPeriod() {
		return nDormantPeriod;
	}
	@Column(name = "V_PASSWORD_SAMEAS_USER")
	public String getvPasswordSameasUser() {
		return vPasswordSameasUser;
	}
	@Column(name = "N_PASSWORD_HISTORY")
	public int getnPasswordHis() {
		return nPasswordHis;
	}
	@Column(name = "V_CHANGEPASSWORD_REQUIRED")
	public String getvChangePassReq() {
		return vChangePassReq;
	}
	@Column(name = "N_ACCNT_LOCKOUT")
	public int getnAccntLockout() {
		return nAccntLockout;
	}
	@Column(name = "V_RELEASE_ACCNTlOCK")
	public String getvReleaseAccntLock() {
		return vReleaseAccntLock;
	}
	@Column(name = "N_TIME_RELEASE_ACCNTLOCK")
	public int getnTimeReleaseAccntLock() {
		return nTimeReleaseAccntLock;
	}
	@Column(name = "N_REQUIRED_CAPTCHA")
	public int getnRequiredCaptcha() {
		return nRequiredCaptcha;
	}
	
	@Column(name = "N_REQUIRED_MOB_OTP")
	public int getnRequiredMobOTP() {
		return nRequiredMobOTP;
	}
	
	public void setnPasswordAge(int nPasswordAge) {
		this.nPasswordAge = nPasswordAge;
	}

	public void setnActivationPeriod(int nActivationPeriod) {
		this.nActivationPeriod = nActivationPeriod;
	}

	public void setnDormantPeriod(int nDormantPeriod) {
		this.nDormantPeriod = nDormantPeriod;
	}

	public void setvPasswordSameasUser(String vPasswordSameasUser) {
		this.vPasswordSameasUser = vPasswordSameasUser;
	}

	public void setnPasswordHis(int nPasswordHis) {
		this.nPasswordHis = nPasswordHis;
	}

	public void setvChangePassReq(String vChangePassReq) {
		this.vChangePassReq = vChangePassReq;
	}

	public void setnAccntLockout(int nAccntLockout) {
		this.nAccntLockout = nAccntLockout;
	}

	public void setvReleaseAccntLock(String vReleaseAccntLock) {
		this.vReleaseAccntLock = vReleaseAccntLock;
	}

	public void setnTimeReleaseAccntLock(int nTimeReleaseAccntLock) {
		this.nTimeReleaseAccntLock = nTimeReleaseAccntLock;
	}

	public void setnRequiredCaptcha(int nRequiredCaptcha) {
		this.nRequiredCaptcha = nRequiredCaptcha;
	}

	public void setvCompPrefix(String vCompPrefix) {
		this.vCompPrefix = vCompPrefix;
	}

	public void setnSettingId(int nSettingId) {
		this.nSettingId = nSettingId;
	}
	
	
	public void setnPasswordLength(int nPasswordLength) {
		this.nPasswordLength = nPasswordLength;
	}
	
	
	public void setvSpecialCharAllow(String vSpecialCharAllow) {
		this.vSpecialCharAllow = vSpecialCharAllow;
	}
	
	
	public void setnMinAlphabetAllow(int nMinAlphabetAllow) {
		this.nMinAlphabetAllow = nMinAlphabetAllow;
	}
	
	
	public void setnMinNoAllow(int nMinNoAllow) {
		this.nMinNoAllow = nMinNoAllow;
	}
	
	
	public void setnMinSpecialAllow(int nMinSpecialAllow) {
		this.nMinSpecialAllow = nMinSpecialAllow;
	}
	
	
	public void setnMinLowerAllow(int nMinLowerAllow) {
		this.nMinLowerAllow = nMinLowerAllow;
	}
	
	
	public void setnMinUpperAllow(int nMinUpperAllow) {
		this.nMinUpperAllow = nMinUpperAllow;
	}
	public void setvSmtpHost(String vSmtpHost) {
		this.vSmtpHost = vSmtpHost;
	}

	public void setvSmtpPort(String vSmtpPort) {
		this.vSmtpPort = vSmtpPort;
	}

	public void setvSmtpUser(String vSmtpUser) {
		this.vSmtpUser = vSmtpUser;
	}

	public void setvSmtpPassword(String vSmtpPassword) {
		this.vSmtpPassword = vSmtpPassword;
	}

	public void setvFromEmail(String vFromEmail) {
		this.vFromEmail = vFromEmail;
	}

	public void setvCsdEmail(String vCsdEmail) {
		this.vCsdEmail = vCsdEmail;
	}
	
	public void setnRequiredMobOTP(int nRequiredMobOTP) {
		this.nRequiredMobOTP = nRequiredMobOTP;
	}
	
	
}
