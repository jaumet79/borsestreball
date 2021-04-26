package com.jrosselloj.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.enums.RolEnum;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.service.IUsuarioService;

@Component
@Scope("session")
public class LanguageBean {
	
	private List<IdiomaEnum> idiomas = new ArrayList<>(Arrays.asList(IdiomaEnum.values()));
	
	private IdiomaEnum idiomaSelected;
	
	private Usuario usuario;
	
	@Autowired
	IUsuarioService usuarioService;
	
	@PostConstruct
	public void init() {
		//idiomaSelected = IdiomaEnum.getIdiomaEnumByLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}
	
	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		
		idiomaSelected = (IdiomaEnum)e.getNewValue();
		
		FacesContext.getCurrentInstance().getViewRoot().setLocale(idiomaSelected.getLocale());
		
	}
	
	public String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
		
	}
	
	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = usuarioService.findById(getUserName());
		}
		
		return usuario;
	}
	
	public String getUserRoles() {
		String roles = "";
		for (GrantedAuthority ggg : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			if (roles != "") {
				roles += ", ";
			}
			roles += ggg.getAuthority();
			
		}
		
		return roles;
		
	}
	
	
	private boolean hadRole(RolEnum rol) {
		boolean hadRole = false;
		
		if (rol != null) {
			String rolString = "ROLE_" + rol.toString();
			for (GrantedAuthority gAuth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
				if (rolString.equalsIgnoreCase(gAuth.getAuthority())) {
					hadRole = true;
				}
			}
		}
		
		return hadRole;
		
	}
	
	
	public boolean isAdmin() {
		return hadRole(RolEnum.ADMIN);
	}
	
	public boolean isEditor() {
		boolean isEditor = false;
		isEditor = isAdmin();
		if (!isEditor) {
			isEditor = hadRole(RolEnum.EDITOR);
		}
		return isEditor;
	}
	
	public boolean isConsultor() {
		boolean isConsultor = false;
		isConsultor = isEditor();
		if (!isConsultor) {
			isConsultor = hadRole(RolEnum.CONSULTOR);
		}
		return isConsultor;
	}
	
	
	public List<IdiomaEnum> getIdiomas() {
		return idiomas;
	}
	
	
	public IdiomaEnum getIdiomaSelected() {
		return idiomaSelected;
	}
	
	
	public void setIdiomaSelected(IdiomaEnum idiomaSelected) {
		this.idiomaSelected = idiomaSelected;
	}
	
}
