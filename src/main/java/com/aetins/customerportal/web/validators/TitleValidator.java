package com.aetins.customerportal.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

@FacesValidator("titleValidator")
public class TitleValidator implements Validator {
    private static final Logger logger = Logger.getLogger(TitleValidator.class);

    /* (non-Javadoc)
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
	
	logger.info("Inside title validator " + arg2.toString());
	
	if (arg2.toString().equalsIgnoreCase("Select Title *")) {
	    FacesMessage msg = new FacesMessage(" title validation failed",
		    "Please select title");
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    logger.info("Inside title validator error");
	    throw new ValidatorException(msg);

	}
    }

}
