package com.aetins.customerportal.web.validators;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

/**
 * @author satendra
 * @since 27/01/2017 
 */
@FacesValidator("mobileNumberValidator")
public class MobileNumberValidator implements Validator {

    private static final Logger logger = Logger.getLogger(MobileNumberValidator.class);

    /* (non-Javadoc)
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext paramFacesContext, UIComponent paramUIComponent, Object paramObject)
	    throws ValidatorException {
	logger.info("Inside mobile number validation " + paramObject.toString());

	String regex = "\\d+";

	if (!paramObject.toString().matches(regex)) {
	    FacesMessage msg = new FacesMessage(" Mobile Number validation failed.",
		    "Please provide the valid Mobile number or contact ");
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    throw new ValidatorException(msg);
	}
    }
}
