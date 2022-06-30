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
@FacesValidator("secretQuestion2")
public class SecretQuestion2 implements Validator
{
    private static final Logger logger = Logger.getLogger(SecretQuestion2.class);

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException
    {
        logger.info("secret question 2 validation " + arg2.toString());

        if (arg2.toString().equalsIgnoreCase("Enter Secret Question-2 *")) {
            FacesMessage msg =
                new FacesMessage("Secret question 2 validation failed", "Please select secret question 2");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            logger.info("Inside Secret question 2 validation  error");
            throw new ValidatorException(msg);

        }
    }

}
