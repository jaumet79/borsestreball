package com.jrosselloj.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GlobalBean {
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public void showInfo(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message, null);
	}
	
}
