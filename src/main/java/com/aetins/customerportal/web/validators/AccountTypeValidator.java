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
@FacesValidator("accountTypeVaidator")
public class AccountTypeValidator implements Validator{
	
	private static final Logger logger = Logger.getLogger(AccountTypeValidator.class);

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub
		logger.info("Inside AccountType Validator");
		if(arg2.toString().equalsIgnoreCase("Select"))
		{
			FacesMessage message = new FacesMessage("Account Type Failed","Please Select Account Type");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			logger.info("Inside Account Type Error");
			throw new ValidatorException(message);
		}
	}
	

}
