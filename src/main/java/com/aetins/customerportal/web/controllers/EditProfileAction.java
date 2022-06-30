package com.aetins.customerportal.web.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;
import com.aetins.customerportal.core.services.IOTPService;
import com.aetins.customerportal.web.audittrails.service.UserSessionAuditTrailsService;
import com.aetins.customerportal.web.common.Constants;
import com.aetins.customerportal.web.common.LoginSession;
import com.aetins.customerportal.web.dao.CustomerLoginDAO;
import com.aetins.customerportal.web.dao.IUserProfileImageDao;
import com.aetins.customerportal.web.dto.CpCustomerDetailDTO;
import com.aetins.customerportal.web.dto.CpUserInfoDTO;
import com.aetins.customerportal.web.dto.MasterListDTO;
import com.aetins.customerportal.web.dto.UserDTO;
import com.aetins.customerportal.web.entity.CPuserProfileImage;
import com.aetins.customerportal.web.entity.CpUserInfo;
import com.aetins.customerportal.web.service.AdminBL;
import com.aetins.customerportal.web.service.CustomerDetailsBL;
import com.aetins.customerportal.web.service.CustomerLoginBL;
import com.aetins.customerportal.web.service.CustomerPortalServices;
import com.aetins.customerportal.web.service.impl.AdminBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerDetailsBLImpl;
import com.aetins.customerportal.web.service.impl.CustomerLoginBLImpl;
import com.aetins.customerportal.web.utils.AppSettingURL;
import com.aetins.customerportal.web.utils.BSLUtils;
import com.aetins.customerportal.web.utils.RestTemplateUtils;

@Controller
@Scope("session")
public class EditProfileAction extends BaseAction {

	@Autowired
	CustomerLoginBL customerLoginBLImpl;

	@Autowired
	AdminBL adminImpl;

	@Autowired
	CustomerDetailsBL customerDetailsBLImpl;

	@Autowired
	CustomerLoginBL customDetails;

	@Autowired
	CustomerPortalServices customerPortalServices;

	@Autowired
	LoginSession loginSession;

	@Autowired
	private CustomerLoginDAO customerDao;

	@Autowired
	private IUserProfileImageDao userProfileImage;

	@Autowired
	UserSessionAuditTrailsService userLoginAuditTrails;

	@Autowired
	IOTPService otpService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	private List<UserDTO> userDetailsList;
	private List<CpUserInfoDTO> userLists;
	private List<CpCustomerDetailDTO> custDetails;
	private CpUserInfoDTO cpUserInfoDTO;
	private CpCustomerDetailDTO cpCustomerDetailDTO;
	CpUserInfo cpUserInfo = new CpUserInfo();
	List<CpUserInfoDTO> cpUserList = new ArrayList<CpUserInfoDTO>();

	UploadedFile file;
	private long fileSize;
	private String croppedImagePath;
	private String eventImageName;
	private String lastName;
	private String firestName;
	private List<MasterListDTO> masterList = new ArrayList<MasterListDTO>();

	private static final Logger logger = LoggerFactory.getLogger(EditProfileAction.class);

	public String getCroppedImagePath() {
		return croppedImagePath;
	}

	public void setCroppedImagePath(String croppedImagePath) {
		this.croppedImagePath = croppedImagePath;
	}

	private CroppedImage croppedImage;

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	String displaynam;

	@Override
	public void init() {
		// For displaying title
		// TODO Auto-generated method stub
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(SecurityLibrary.getFullLoggedInUser().getVuserName());
		cpUserList = customDetails.fetchUserDetails(userDTO);
		Iterator<CpUserInfoDTO> it = cpUserList.iterator();
		while (it.hasNext()) {
			cpCustomerDetailDTO = new CpCustomerDetailDTO();
			cpUserInfoDTO = it.next();
			cpUserInfoDTO.getVprefName();
			cpUserInfoDTO.getVcontactNo();
			cpUserInfoDTO.getVtitle();
			cpUserInfoDTO.getVuserName();
			cpUserInfoDTO.getVemail();
			cpUserInfoDTO.getDdob();
			cpUserInfoDTO.getRoles();
			custDetails = customerDetailsBLImpl.customerDetails(cpUserInfoDTO.getNcustRefNo());
			Iterator<CpCustomerDetailDTO> its = custDetails.iterator();
			while (its.hasNext()) {
				cpCustomerDetailDTO = its.next();
				cpCustomerDetailDTO.setVidType(idenTypeDesc(cpCustomerDetailDTO.getVidType()));
				if (BSLUtils.isNotNull(cpCustomerDetailDTO.getVcustName())) {
					String[] tmpLastName = cpCustomerDetailDTO.getVcustName().split(" ");
					if (tmpLastName.length >= 1) {
						firestName = tmpLastName[0].equals("null") ? "" : tmpLastName[0];
					} else {
						firestName = "";
					}
					if (tmpLastName.length >= 1) {
						if (!(tmpLastName.length == 1)) {
							lastName = tmpLastName[tmpLastName.length - 1].equals("null") ? ""
									: tmpLastName[tmpLastName.length - 1];
						}
					} else {
						lastName = "";
					}

				}
				Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
				if (BSLUtils.isNotNull(cpCustomerDetailDTO)) {
					String tmpCustGender = cpCustomerDetailDTO.getGender();
					if (BSLUtils.isNotNull(tmpCustGender) && !tmpCustGender.isEmpty()) {
						logger.info("EditProfile Title list call entering time:@@@@@@@:" + (new Date()).getMinutes()
								+ ":" + (new Date()).getSeconds());
						if (tmpCustGender.equalsIgnoreCase("M")) {
							masterList = customerPortalServices.getMasterLov(
									locale.toString().equalsIgnoreCase("ar") ? "AR" : "EN", Constants.MTITLE);
						} else if (tmpCustGender.equalsIgnoreCase("F")) {
							masterList = customerPortalServices.getMasterLov(
									locale.toString().equalsIgnoreCase("ar") ? "AR" : "EN", Constants.FTITLE);
						} else {
							masterList = customerPortalServices.getMasterLov(
									locale.toString().equalsIgnoreCase("ar") ? "AR" : "EN", Constants.TITLE);
						}
						logger.info("EditProfile Title list call ending time:@@@@@@@:" + (new Date()).getMinutes() + ":"
								+ (new Date()).getSeconds());
					}
				}
				String filePath = AppSettingURL.IMAGE_LOCATION;
				File targetFolder = new File(filePath);
				byte[] bytes = null;
				if (null != file) {
					bytes = file.getContents();
					String filename = FilenameUtils.getName(file.getFileName());
					try {
						if (!targetFolder.exists())
							targetFolder.mkdir();
						BufferedOutputStream stream = new BufferedOutputStream(
								new FileOutputStream(new File(filePath + filename)));
						stream.write(bytes);
						stream.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		}
	}

	public void btnEditProfSubmit() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		try {
			if (BSLUtils.isNotNull(file))
				if (file.getSize() > 500000) {
					if (locale.toString().equalsIgnoreCase("ar")) {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "رسالة",
								"حجم صورة العرض يتجاوز الحد (500 كيلو بايت) حاول باستخدام صورة ذات دقة أقل.");
					} else {
						message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
								"Display Image Size exceeds the limit (500KB) try with a lower resolution image.");
					}
					PrimeFaces.current().dialog().showMessageDynamic(message);
					return;
				}
			System.out.println(cpUserInfoDTO.getVprefName());
			System.out.println(cpUserInfoDTO.getVtitle());
			System.out.println(cpUserInfoDTO.getnImageId());
			/*
			 * if (croppedImagePath != null) { ExternalContext externalContext =
			 * FacesContext.getCurrentInstance().getExternalContext(); String
			 * finalImageActualLocation = externalContext.getRealPath("") + File.separator +
			 * "resources" + File.separator + "profileImage" + File.separator +
			 * String.valueOf(cpCustomerDetailDTO.getNcustRefNo()).trim() + ".jpg";
			 * copyFile(new File(croppedImagePath), new File(finalImageActualLocation)); }
			 */
			/*
			 * CpUserInfo findByUserName =
			 * customerDao.findByUserName(cpUserInfoDTO.getVuserName());
			 * findByUserName.setVemail(cpUserInfoDTO.getVemail());
			 * findByUserName.setVprefName(cpUserInfoDTO.getVprefName());
			 */
			// boolean updateUserDetails = customerDao.updateUserDetails(findByUserName);
			boolean status = customerLoginBLImpl.updateUserDetails(cpUserInfoDTO);

			CPuserProfileImage profileImage = null;
			if (file != null) {

				profileImage = new CPuserProfileImage();
				profileImage.setUsername(cpUserInfoDTO.getVuserName());
				profileImage.setFileName(file.getFileName());
			}

			CPuserProfileImage loadUserByUserName = userProfileImage.loadUserByUserName(cpUserInfoDTO.getVuserName());

			boolean saveProfileImage = false;
			if (file != null && loadUserByUserName != null) {
				logger.info("UPDATE USER PROFILE IMAGE");
				loadUserByUserName.setFileName(file.getFileName());
				saveProfileImage = userProfileImage.updateProfileImage(loadUserByUserName);
			} else {
				if (file != null) {

					logger.info("SAVE USER PROFILE IMAGE");
					saveProfileImage = userProfileImage.saveProfileImage(profileImage);
				}
			}

			// PrimeFaces.current().executeScript("$('#services-1').modal('show');");

			if (status || saveProfileImage) {
				/*
				 * FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				 * "Message", "Profile Updated Successfully.");
				 */
				// PrimeFaces.current().dialog().showMessageDynamic(message);
				PrimeFaces.current().executeScript("PF('profDiag').show()");
				enabling = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean enabling = false;

	public void btnSubmit() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		enabling = false;
		if (BSLUtils.isNotNull(file)) {
			btnEditProfSubmit();
		} else {
			enabling = true;
			if (locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "رسالة",
						"يرجى تحديد صورة الملف الشخصي وتحديث الحساب. إذا كنت ترغب في المتابعة ، يرجى النقر فوق الزر \"موافق\" أدناه للمتابعة.");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
						"Please select the profile picture and update the account. If you want to continue please click the below button OK button to continue.");
			}
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}

	public void btnLogout() {
		try {

			/*
			 * final HttpSession session = request.getSession(); if (session != null) {
			 * 
			 * // Logout user name ServletRequestAttributes attributes =
			 * (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			 * CpUserInfo attribute = (CpUserInfo)
			 * attributes.getRequest().getSession(true).getAttribute(
			 * SessionAttributesConstants._USER_DETAILS);
			 * 
			 * //Fetch login audit trails CpLoginAuditTrails fetchAuditTrailByCurrentDate
			 * =userLoginAuditTrails.fetchAuditTrailsByDate(attribute.getVuserName());
			 * fetchAuditTrailByCurrentDate.setUserLastLoginDate(
			 * fetchAuditTrailByCurrentDate.getLoggedInTime());
			 * fetchAuditTrailByCurrentDate.setLogoutTime(new Date());
			 * userLoginAuditTrails.updateAuditTrail(fetchAuditTrailByCurrentDate); logger.
			 * info("Logout Audit Trail in EditProfileAction for username: {}, logout time: {}"
			 * ,attribute.getVuserName(),fetchAuditTrailByCurrentDate.getLogoutTime());
			 * 
			 * 
			 * List<String> cookiesToClear =
			 * Arrays.asList("JSESSIONID","harmony_expandeditems"); for (String cookieName :
			 * cookiesToClear) { Cookie cookie = new Cookie(cookieName, null); String
			 * cookiePath = request.getContextPath(); if
			 * (!StringUtils.hasLength(cookiePath)) { cookiePath = "/"; }
			 * cookie.setPath(cookiePath); cookie.setMaxAge(0); response.addCookie(cookie);
			 * }
			 * 
			 * //userLoginAuditTrails.updateLogOutTime(fetchAuditTrailByCurrentDate);
			 * otpService.clearOTP(attribute.getVuserName());
			 * logger.info("User Otp remove from cache while logout in EditProfileAction");
			 * session.removeAttribute(SessionAttributesConstants._USER);
			 * session.removeAttribute(SessionAttributesConstants._USER_DETAILS);
			 * logger.info("User session removed from EditProfileAction: {}",session.
			 * getAttribute(SessionAttributesConstants._USER));
			 * logger.info("User Otp remove from cache from EditProfileAction");
			 * session.invalidate();
			 * 
			 * 
			 * }
			 */
			RestTemplate restTemplate = RestTemplateUtils.restTemplate();
			String headerName = (String) request.getAttribute("_csrf.headerName");
			String headerValue = (String)request.getAttribute("_csrf.token");
			
			HttpHeaders headers = new HttpHeaders();
			headers.set(headerName, headerValue);
			/* Entity with body & headers */
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);			
			ResponseEntity<String> response = restTemplate.postForEntity(SecurityLibrary.getAppContextPath() + "/j_spring_security_logout", requestEntity, String.class);

			/*
			 * FacesContext context = FacesContext.getCurrentInstance();
			 * context.getExternalContext().redirect();
			 */
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void anotherbutton() {
		btnEditProfSubmit();
	}

	public boolean isEnabling() {
		return enabling;
	}

	public void setEnabling(boolean enabling) {
		this.enabling = enabling;
	}

	public List<UserDTO> getUserDetailsList() {
		return userDetailsList;
	}

	public List<CpUserInfoDTO> getUserLists() {
		return userLists;
	}

	public CpUserInfoDTO getCpUserInfoDTO() {
		return cpUserInfoDTO;
	}

	public CpCustomerDetailDTO getCpCustomerDetailDTO() {
		return cpCustomerDetailDTO;
	}

	public void setAdminImpl(AdminBLImpl adminImpl) {
		this.adminImpl = adminImpl;
	}

	public void setCustomerDetailsBLImpl(CustomerDetailsBLImpl customerDetailsBLImpl) {
		this.customerDetailsBLImpl = customerDetailsBLImpl;
	}

	public void setUserDetailsList(List<UserDTO> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	public void setUserLists(List<CpUserInfoDTO> userLists) {
		this.userLists = userLists;
	}

	public void setCustDetails(List<CpCustomerDetailDTO> custDetails) {
		this.custDetails = custDetails;
	}

	public void setCpUserInfoDTO(CpUserInfoDTO cpUserInfoDTO) {
		this.cpUserInfoDTO = cpUserInfoDTO;
	}

	public void setCpCustomerDetailDTO(CpCustomerDetailDTO cpCustomerDetailDTO) {
		this.cpCustomerDetailDTO = cpCustomerDetailDTO;
	}

	public CpUserInfo getCpUserInfo() {
		return cpUserInfo;
	}

	public List<CpUserInfoDTO> getCpUserList() {
		return cpUserList;
	}

	public void setCpUserInfo(CpUserInfo cpUserInfo) {
		this.cpUserInfo = cpUserInfo;
	}

	public void setCpUserList(List<CpUserInfoDTO> cpUserList) {
		this.cpUserList = cpUserList;
	}

	public void setCustomDetails(CustomerLoginBLImpl customDetails) {
		this.customDetails = customDetails;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	private String filenaming;
	private boolean showing = false;

	public void uploadImage(FileUploadEvent event) {
		filenaming = null;
		showing = false;
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		fileSize = event.getFile().getSize();// 581 KB (595,284 bytes)
		if (fileSize > 500000) {
			if (locale.toString().equalsIgnoreCase("ar")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"رسالة", "حجم صورة العرض يتجاوز الحد (500 كيلو بايت) ، " + "حاول باستخدام صورة ذات دقة أقل"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Message",
						"Display Image Size exceeds the limit (500KB), " + "try with a lower resolution image"));
			}
		} else {

		}

		// Need to upgrade primefaces 7.0
		// RequestContext.getCurrentInstance().reset("dialogForm:croppedImage");
		setStoredImageLocaton("");
		setRenderCropPanel(false);
		// PrimeFaces.current().executeScript("PF('dlg1').hide()");
		if (event != null) {
			file = event.getFile();
			setEventImageName(event.getFile().getFileName());
			System.out.println(file.getFileName());

			// String filePath=AppSettingURL.IMAGE_LOCATION;
			/*
			 * ExternalContext externalContext =
			 * FacesContext.getCurrentInstance().getExternalContext(); String filePath =
			 * externalContext.getRealPath("") + File.separator + "resources" +
			 * File.separator + "crop" + File.separator;
			 */
			// String filePath =
			// AppSettingURL.IMAGE_LOCATION+"/"+cpUserInfoDTO.getVuserName()+"/";
			String filePath = AppSettingURL.IMAGE_LOCATION + "\\resources\\" + "cp-layout\\" + "profile_images\\"
					+ cpUserInfoDTO.getVuserName() + "\\";
			File targetFolder = new File(filePath);
			byte[] bytes = null;
			if (file != null) {
				bytes = file.getContents();
				String extension = FilenameUtils.getExtension(file.getFileName());
				String filename = file.getFileName();
				filenaming = file.getFileName();
				System.out.println("File Name:::" + filenaming);
				if (filenaming != null) {
					showing = true;
				} else {
					showing = false;
				}
				try {

					logger.info("************************");
					logger.info("*****PROFILE IMAGE******");
					logger.info("FILE PATH: " + filePath);

					if (!targetFolder.exists()) {
						targetFolder.mkdir();
					}
					String uploadedImage = filePath + filename;
					System.out.println("uploadedImage:" + uploadedImage);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(uploadedImage)));
					stream.write(bytes);
					stream.flush();
					stream.close();
					// setRenderCropPanel(true);
					// PrimeFaces.current().executeScript("PF('dlg1').show()");
					// setStoredImageLocaton("/" + "resources" + "/" + "crop" + "/" + filename);

					System.out.println(storedImageLocaton);

				} catch (IOException e) {
					e.printStackTrace();
					logger.error("Exception caught while processing profile image " + e.getCause());
				}
			}

		}
	}

	public boolean isShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
	}

	public String getFilenaming() {
		return filenaming;
	}

	public void setFilenaming(String filenaming) {
		this.filenaming = filenaming;
	}

	public void uploadImage1() {
		System.out.println("CROP METHOD IS CALLEDDDDDDDDDDDDDDDDDDDDDDD");
	}

	public void crop() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		setRenderCropPanel(true);
		PrimeFaces.current().executeScript("PF('dlg1').show()");
		if (croppedImage == null) {
			return;
		}
		String custRef = "";
		if (cpCustomerDetailDTO != null) {
			if (cpCustomerDetailDTO != null) {
				custRef = "temp1" + String.valueOf(cpCustomerDetailDTO.getNcustRefNo());
			}
		}
		if (custRef != null && !custRef.equalsIgnoreCase("") && !custRef.equalsIgnoreCase(null)) {
			setNewImageName(custRef);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			croppedImagePath = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "crop"
					+ File.separator + getNewImageName() + ".jpg";

			FileImageOutputStream imageOutput;
			try {
				System.out.println("cropped newFileName:" + croppedImagePath);
				File fileTemp = new File(croppedImagePath);
				if (fileTemp.exists()) {
					fileTemp.delete();
				}
				imageOutput = new FileImageOutputStream(new File(croppedImagePath));
				imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
				imageOutput.close();

				saveCroppedImage();
			} catch (Exception e) {
				if (locale.toString().equalsIgnoreCase("ar")) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "رسالة", "فشل القص");
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Cropping failed");
				}
				PrimeFaces.current().dialog().showMessageDynamic(message);
				return;
			}
			if (locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة", "الانتهاء من الاقتصاص");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Cropping finished");
			}
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}

	}

	public void saveCroppedImage() {
		System.out.println("called saveCroppedImage method");
		setRenderCropPanel(false);
		PrimeFaces.current().executeScript("PF('dlg1').hide()");
	}

	public void cancelCroppedImage() {
		System.out.println("called cancelCroppedImage method");
		setRenderCropPanel(false);
		PrimeFaces.current().executeScript("PF('dlg1').hide()");
	}

	private String getRandomImageName() {
		int i = (int) (Math.random() * 100000);

		return String.valueOf(i);
	}

	private String newImageName;
	private boolean renderCropPanel;
	private String storedImageLocaton;
	private String profileImagePath = "";

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public String getStoredImageLocaton() {
		return storedImageLocaton;
	}

	public void setStoredImageLocaton(String storedImageLocaton) {
		this.storedImageLocaton = storedImageLocaton;
	}

	public boolean isRenderCropPanel() {
		return renderCropPanel;
	}

	public void setRenderCropPanel(boolean renderCropPanel) {
		this.renderCropPanel = renderCropPanel;
	}

	public String getNewImageName() {
		return newImageName;
	}

	public void setNewImageName(String newImageName) {
		this.newImageName = newImageName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void copyFile(File sourceFile, File destinationFile) {
		try {
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

			int bufferSize;
			byte[] bufffer = new byte[512];
			while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
				fileOutputStream.write(bufffer, 0, bufferSize);
			}
			fileInputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayvalid() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		FacesMessage message = new FacesMessage();
		if (cpUserInfoDTO.getVprefName() != null) {

		} else {
			if (locale.toString().equalsIgnoreCase("ar")) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "رسالة", "لا يمكن أن يكون اسم العرض فارغًا");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Display Name Cannot be Empty");
			}
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}

//	       Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
//	       if(cpUserInfoDTO.getVprefName()!=null) {
//	    	   int i = cpUserInfoDTO.getVprefName().length();
//	    	   Matcher matcher = pattern.matcher(cpUserInfoDTO.getVprefName());
//	    	   if(i<=20){
//	    		   if (!matcher.matches()) {
//	    			   FacesMessage message= new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
//	   						"Display Name Should Not Contain Any Speical Characters.");
//	    			   PrimeFaces.current().dialog().showMessageDynamic(message);
//	    	      } 
//	    	   }else {
//	    		   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
//	    				   "Display Name Should Exceed More Than 20 Characters.");  
//	    		   PrimeFaces.current().dialog().showMessageDynamic(message);
//	    	   } 
//	       }else {
//	    	   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
//						"Display Name Cannot be Empty.");
//	    	   PrimeFaces.current().dialog().showMessageDynamic(message);
//	       }	      
	}

	public void onShowDialogue(int sleep) {
		System.out.println("onShowDialogue method called");
		try {
			// need to upgrade primefaces 7.0
			// RequestContext.getCurrentInstance().reset("dialogForm:croppedImage");
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished");
	}

	public String loadImage() {
		if (storedImageLocaton != null && storedImageLocaton != "" && !storedImageLocaton.equalsIgnoreCase(null)
				&& !storedImageLocaton.equalsIgnoreCase("")) {
			System.out.println(storedImageLocaton);
			return storedImageLocaton;
		}
		return "";

	}

	public String loadProfileImage() {
		setProfileImagePath("");
		if (cpCustomerDetailDTO != null) {
			if (String.valueOf(cpCustomerDetailDTO.getNcustRefNo()) != null) {
				setProfileImagePath("resources/profileImage/"
						+ String.valueOf(cpCustomerDetailDTO.getNcustRefNo()).trim() + ".jpg");
				return profileImagePath;
			}
		}

		return "";

	}

	public String getEventImageName() {
		return eventImageName;
	}

	public void setEventImageName(String eventImageName) {
		this.eventImageName = eventImageName;
	}

	public String getFirestName() {
		return firestName;
	}

	public void setFirestName(String firestName) {
		this.firestName = firestName;
	}

	public List<MasterListDTO> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<MasterListDTO> masterList) {
		this.masterList = masterList;
	}

	public String getDisplaynam() {
		return displaynam;
	}

	public void setDisplaynam(String displaynam) {
		this.displaynam = displaynam;
	}

}