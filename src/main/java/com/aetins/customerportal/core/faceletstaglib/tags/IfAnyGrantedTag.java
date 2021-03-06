package com.aetins.customerportal.core.faceletstaglib.tags;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributeException;
import javax.faces.view.facelets.TagHandler;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;

public class IfAnyGrantedTag extends TagHandler {

	private final TagAttribute roles;

	public void apply(FaceletContext faceletContext, UIComponent uiComponent)
			throws IOException, FacesException, FaceletException, ELException {
		if (this.roles == null)
			throw new FaceletException("roles must be given, but is null");

		String roles = this.roles.getValue(faceletContext);
		if (roles == null || "".equals(roles.trim()))
			throw new FaceletException("roles must be given");

		if (SecurityLibrary.ifAnyGranted(roles))
			this.nextHandler.apply(faceletContext, uiComponent);
	}

	public IfAnyGrantedTag(ComponentConfig componentConfig) {
		super(componentConfig);
		this.roles = this.getRequiredAttribute("roles");
		if (this.roles == null)
			throw new TagAttributeException(this.roles, "The `roles` attribute has to be specified!");

	}

}