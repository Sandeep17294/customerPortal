/**
 * 
 */
package com.aetins.customerportal.core.utils;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.FacesRequestAttributes;


/**
 * @author avinash
 *
 */
@Component
public class ViewScope implements Scope {

	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.Scope#get(java.lang.String, org.springframework.beans.factory.ObjectFactory)
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			if (getViewMap().containsKey(name)) {
				return getViewMap().get(name);
			} else {
				Object object = objectFactory.getObject();
				getViewMap().put(name, object);
				return object;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {
		Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		if (callbacks != null) {
			callbacks.put(name, runnable);
		}
	}

	@Override
	public Object resolveContextualObject(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.resolveReference(name);
	}

	@Override
	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object remove(String name) {
		Object instance = getViewMap().remove(name);
		if (instance != null) {
			Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			if (callbacks != null) {
				callbacks.remove(name);
			}
		}
		return instance;
	}

	private Map<String, Object> getViewMap() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}
}
