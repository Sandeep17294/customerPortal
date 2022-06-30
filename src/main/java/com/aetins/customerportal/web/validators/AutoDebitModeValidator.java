package com.aetins.customerportal.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
/**
 * @author : Bhagavathy
 * 
 */

@FacesValidator("autoDebitModeValidator")
public class AutoDebitModeValidator implements Validator{
	
	private static final Logger logger = Logger.getLogger(AutoDebitModeValidator.class);

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		logger.info("Inside AutoDebitMode" + arg2.toString());
		if(arg2.toString().equalsIgnoreCase("Select")){
			FacesMessage msg = new FacesMessage("AutoDebit Mode failed","Please select AutoDebit Mode");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			logger.info("Inside AutoDebit Mode Error");
			throw new ValidatorException(msg);
		}
	}

}
