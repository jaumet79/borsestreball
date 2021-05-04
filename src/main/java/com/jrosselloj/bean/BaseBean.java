package com.jrosselloj.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseBean {
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public void showInfo(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message, null);
	}
	
	public void showError(String message) {
		addMessage(FacesMessage.SEVERITY_ERROR, message, null);
	}
}
