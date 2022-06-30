package com.aetins.customerportal.web.dto;

import java.util.Date;

public class PasswordRulesDTO {

	private int nSettingId;
	private int nPasswordLength;
	private String vSpecialCharAllow;
	private int nMinAlphabetAllow;
	private int nMinNoAllow;
	private int nMinSpecialAllow;
	private int nMinLowerAllow;
	private int nMinUpperAllow;
	private String vCompPrefix;
	private String vTermsCond;
	private Date dTermsCondDate;
	private String vIntroAdvImagePath;
	private String vIntroAdvImageUrl;
	private Date dIntroAdvImageDate;
	private int vIntroAdvImgPeriod;
	private String vAllAdvImgPath;
	private String vAllAdvImgUrl;
	private Date dAllAdvImgDate;
	private int vAllAdvImgPeriod;
	private String vSmtpHost;
	private String vSmtpPort;
	private String vSmtpUser;
	private String vSmtpPassword;
	private String vFromEmail;
	private int nRecordListCNT;
	private String vPremiumRecpReportLink;
	private String vLapsedLetterReportLink;
	private String vPortFolioReportLink;
	private int nPasswordAge;
	private int nMaxLogin;
	private String vCsdEmail;
	private String vTncEng;
	private String vTncArb;
	private int nActivationPeriod;
	private int nDormantPeriod;
	private String vPasswordSameasUser;
	private int nPasswordHis;
	private String vChangePassReq;
	private int nAccntLockout;
	private String vReleaseAccntLock;
	private int nTimeReleaseAccntLock;
	private int nRequiredCaptcha;
	private int nRequiredMobOTP;
	private int nQuestionCount;

	public int getnSettingId() {
		return nSettingId;
	}

	public void setnSettingId(int nSettingId) {
		this.nSettingId = nSettingId;
	}

	public int getnPasswordLength() {
		return nPasswordLength;
	}

	public void setnPasswordLength(int nPasswordLength) {
		this.nPasswordLength = nPasswordLength;
	}

	public String getvSpecialCharAllow() {
		return vSpecialCharAllow;
	}

	public void setvSpecialCharAllow(String vSpecialCharAllow) {
		this.vSpecialCharAllow = vSpecialCharAllow;
	}

	public int getnMinAlphabetAllow() {
		return nMinAlphabetAllow;
	}

	public void setnMinAlphabetAllow(int nMinAlphabetAllow) {
		this.nMinAlphabetAllow = nMinAlphabetAllow;
	}

	public int getnMinNoAllow() {
		return nMinNoAllow;
	}

	public void setnMinNoAllow(int nMinNoAllow) {
		this.nMinNoAllow = nMinNoAllow;
	}

	public int getnMinSpecialAllow() {
		return nMinSpecialAllow;
	}

	public void setnMinSpecialAllow(int nMinSpecialAllow) {
		this.nMinSpecialAllow = nMinSpecialAllow;
	}

	public int getnMinLowerAllow() {
		return nMinLowerAllow;
	}

	public void setnMinLowerAllow(int nMinLowerAllow) {
		this.nMinLowerAllow = nMinLowerAllow;
	}

	public int getnMinUpperAllow() {
		return nMinUpperAllow;
	}

	public void setnMinUpperAllow(int nMinUpperAllow) {
		this.nMinUpperAllow = nMinUpperAllow;
	}

	public String getvCompPrefix() {
		return vCompPrefix;
	}

	public void setvCompPrefix(String vCompPrefix) {
		this.vCompPrefix = vCompPrefix;
	}

	public String getvTermsCond() {
		return vTermsCond;
	}

	public Date getdTermsCondDate() {
		return dTermsCondDate;
	}

	public String getvIntroAdvImagePath() {
		return vIntroAdvImagePath;
	}

	public String getvIntroAdvImageUrl() {
		return vIntroAdvImageUrl;
	}

	public Date getdIntroAdvImageDate() {
		return dIntroAdvImageDate;
	}

	public int getvIntroAdvImgPeriod() {
		return vIntroAdvImgPeriod;
	}

	public String getvAllAdvImgPath() {
		return vAllAdvImgPath;
	}

	public String getvAllAdvImgUrl() {
		return vAllAdvImgUrl;
	}

	public Date getdAllAdvImgDate() {
		return dAllAdvImgDate;
	}

	public int getvAllAdvImgPeriod() {
		return vAllAdvImgPeriod;
	}

	public String getvSmtpHost() {
		return vSmtpHost;
	}

	public String getvSmtpPort() {
		return vSmtpPort;
	}

	public String getvSmtpUser() {
		return vSmtpUser;
	}

	public String getvSmtpPassword() {
		return vSmtpPassword;
	}

	public String getvFromEmail() {
		return vFromEmail;
	}

	public int getnRecordListCNT() {
		return nRecordListCNT;
	}

	public String getvPremiumRecpReportLink() {
		return vPremiumRecpReportLink;
	}

	public String getvLapsedLetterReportLink() {
		return vLapsedLetterReportLink;
	}

	public String getvPortFolioReportLink() {
		return vPortFolioReportLink;
	}

	public int getnPasswordAge() {
		return nPasswordAge;
	}

	public int getnMaxLogin() {
		return nMaxLogin;
	}

	public void setvTermsCond(String vTermsCond) {
		this.vTermsCond = vTermsCond;
	}

	public void setdTermsCondDate(Date dTermsCondDate) {
		this.dTermsCondDate = dTermsCondDate;
	}

	public void setvIntroAdvImagePath(String vIntroAdvImagePath) {
		this.vIntroAdvImagePath = vIntroAdvImagePath;
	}

	public void setvIntroAdvImageUrl(String vIntroAdvImageUrl) {
		this.vIntroAdvImageUrl = vIntroAdvImageUrl;
	}

	public void setdIntroAdvImageDate(Date dIntroAdvImageDate) {
		this.dIntroAdvImageDate = dIntroAdvImageDate;
	}

	public void setvIntroAdvImgPeriod(int vIntroAdvImgPeriod) {
		this.vIntroAdvImgPeriod = vIntroAdvImgPeriod;
	}

	public void setvAllAdvImgPath(String vAllAdvImgPath) {
		this.vAllAdvImgPath = vAllAdvImgPath;
	}

	public void setvAllAdvImgUrl(String vAllAdvImgUrl) {
		this.vAllAdvImgUrl = vAllAdvImgUrl;
	}

	public void setdAllAdvImgDate(Date dAllAdvImgDate) {
		this.dAllAdvImgDate = dAllAdvImgDate;
	}

	public void setvAllAdvImgPeriod(int vAllAdvImgPeriod) {
		this.vAllAdvImgPeriod = vAllAdvImgPeriod;
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

	public void setnRecordListCNT(int nRecordListCNT) {
		this.nRecordListCNT = nRecordListCNT;
	}

	public void setvPremiumRecpReportLink(String vPremiumRecpReportLink) {
		this.vPremiumRecpReportLink = vPremiumRecpReportLink;
	}

	public void setvLapsedLetterReportLink(String vLapsedLetterReportLink) {
		this.vLapsedLetterReportLink = vLapsedLetterReportLink;
	}

	public void setvPortFolioReportLink(String vPortFolioReportLink) {
		this.vPortFolioReportLink = vPortFolioReportLink;
	}

	public void setnPasswordAge(int nPasswordAge) {
		this.nPasswordAge = nPasswordAge;
	}

	public void setnMaxLogin(int nMaxLogin) {
		this.nMaxLogin = nMaxLogin;
	}

	public String getvCsdEmail() {
		return vCsdEmail;
	}

	public void setvCsdEmail(String vCsdEmail) {
		this.vCsdEmail = vCsdEmail;
	}

	public String getvTncEng() {
		return vTncEng;
	}

	public String getvTncArb() {
		return vTncArb;
	}

	public void setvTncEng(String vTncEng) {
		this.vTncEng = vTncEng;
	}

	public void setvTncArb(String vTncArb) {
		this.vTncArb = vTncArb;
	}

	public int getnActivationPeriod() {
		return nActivationPeriod;
	}

	public int getnDormantPeriod() {
		return nDormantPeriod;
	}

	public String getvPasswordSameasUser() {
		return vPasswordSameasUser;
	}

	public int getnPasswordHis() {
		return nPasswordHis;
	}

	public String getvChangePassReq() {
		return vChangePassReq;
	}

	public int getnAccntLockout() {
		return nAccntLockout;
	}

	public String getvReleaseAccntLock() {
		return vReleaseAccntLock;
	}

	public int getnTimeReleaseAccntLock() {
		return nTimeReleaseAccntLock;
	}

	public int getnRequiredCaptcha() {
		return nRequiredCaptcha;
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

	public int getnRequiredMobOTP() {
		return nRequiredMobOTP;
	}

	public void setnRequiredMobOTP(int nRequiredMobOTP) {
		this.nRequiredMobOTP = nRequiredMobOTP;
	}

	public int getnQuestionCount() {
		return nQuestionCount;
	}

	public void setnQuestionCount(int nQuestionCount) {
		this.nQuestionCount = nQuestionCount;
	}

}
