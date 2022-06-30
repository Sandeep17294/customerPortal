package com.aetins.customerportal.core.faceletstaglib.tags;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributeException;
import javax.faces.view.facelets.TagHandler;

import com.aetins.customerportal.core.faceletstaglib.SecurityLibrary;

public class IfGrantAllTag extends TagHandler{

	private final TagAttribute roles;
	
	public IfGrantAllTag(ComponentConfig config) {
		super(config);
		this.roles = this.getRequiredAttribute("roles");
		if (this.roles == null)
			throw new TagAttributeException(this.roles,"The `roles` attribute has to be specified!");
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
		
		if (this.roles == null)
			throw new FaceletException("roles must be given, but is null");

		String roles = this.roles.getValue(ctx);
		if (roles == null || "".equals(roles.trim()))
			throw new FaceletException("roles must be given");

		if (SecurityLibrary.ifAllGranted(roles))
			this.nextHandler.apply(ctx, parent);
	}

}
