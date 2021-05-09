package com.jrosselloj.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.jrosselloj.model.Usuario;

public class BaseBean {
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public void showInfo(String message, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, message, detail);
	}
	
	public void showError(String message, String detail) {
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
	
}
