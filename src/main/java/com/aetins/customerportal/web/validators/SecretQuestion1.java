package com.aetins.customerportal.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

@FacesValidator("secretQuestion1")
public class SecretQuestion1 implements Validator
{
    private static final Logger logger = Logger.getLogger(SecretQuestion1.class);

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException
    {
        logger.info("Inside  secret question 1 validation " + arg2.toString());

        if (arg2.toString().equalsIgnoreCase("Enter Secret Question-1 *")) {
            FacesMessage msg =
                new FacesMessage(" Secret question 1  failed", "Please select Secret question 1");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            logger.info("Secret question 1 validation  error");
            throw new ValidatorException(msg);

        }
    }

}
