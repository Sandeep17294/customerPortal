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
 *
 */
@FacesValidator("secretImageValidator")
public class SecretImageValidator implements Validator {
    private static final Logger logger = Logger.getLogger(SecretImageValidator.class);

    /*
     * (non-Javadoc)
     * 
     * @see javax.faces.validator.Validator#validate(javax.faces.context.
     * FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
	logger.info("secret image validator " + arg2.toString());

	Integer value = (Integer) arg2;
	
	

	if (value.intValue()==0) {
	    FacesMessage msg = new FacesMessage(" secret image Validator Error", "Please select secret image");
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    logger.info("ecret image Validator Error");
	    throw new ValidatorException(msg);

	}

    }
}
