package com.aetins.customerportal.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

/**
 * 
 * @author bhagavathy
 *
 */
@FacesValidator("termsandcond")
public class TermsAndConditionValidator implements Validator{
	
	private static final Logger logger = Logger.getLogger(TermsAndConditionValidator.class);

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		
		logger.info("Inside Terms and condition" + arg2.toString());
		if(arg2.toString().equalsIgnoreCase("false")){
			FacesMessage msg = new FacesMessage("Terms and Select Failed","Please select terms and Conditions");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			logger.info("Inside Terms and condition Mode Error");
			throw new ValidatorException(msg);
		}
			
	}

}
