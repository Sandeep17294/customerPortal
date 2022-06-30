package com.aetins.customerportal.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

/**
 * @author satendra
 * @since 27/01/2017 
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private static final Logger logger = Logger.getLogger(EmailValidator.class);

    /* (non-Javadoc)
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

	logger.info("inside email validation  " + value.toString());

	try {
	    InternetAddress emailAddr = new InternetAddress(value.toString());
	    emailAddr.validate();

	} catch (AddressException e) {
	    FacesMessage msg = new FacesMessage(" E-mail validation failed.","Please provide the valid Email address or contact ");
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    throw new ValidatorException(msg);
	}

    }
}
