package com.jrosselloj.bean;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.jrosselloj.model.Usuario;

/**
 * Clase comú per a tots els beans amb distintes utilitats
 * 
 * @author Jaume
 */
public class BaseBean {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public void showInfo(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message, null);
	}
	
	public void showError(String message) {
		addMessage(FacesMessage.SEVERITY_ERROR, message, null);
	}
	
	public Usuario getUsuario() {
		SessionBean sessionBean = (SessionBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("scopedTarget.sessionBean");
		
		return sessionBean.getUsuario();
	}
	
	public String getParametro(String parametro) {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		return request.getParameter(parametro);
	}
	
	public String getMessage(String clave) {
		try {
			Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			return messageSource.getMessage(clave, null, locale);
			
		} catch (NoSuchMessageException e) {
			return "¿¿" + clave + "??";
			
		}
	}
	
}
