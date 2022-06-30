package com.aetins.customerportal.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.aetins.customerportal.web.controllers.BaseAction;

/**
 * @author satendra
 *
 */
@FacesValidator("termsConditionsValidator")
public class TermsConditionsValidator extends BaseAction implements Validator {
    private static final Logger logger = Logger.getLogger(TermsConditionsValidator.class);

    /* (non-Javadoc)
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
	logger.info("Terms Conditions Validator " + arg2.toString());

	Boolean value = (Boolean) arg2;
	
	if (value.booleanValue()==false) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
				"Please Select Terms And Condition.");
		PrimeFaces.current().dialog().showMessageDynamic(message);
	    logger.info("Terms Conditions Validator Error");
	    throw new ValidatorException(message);
	}

    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
