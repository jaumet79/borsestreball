package com.jrosselloj.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.jrosselloj.MyUserDetails;
import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.enums.RolEnum;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.service.IUsuarioService;

/**
 * Classe de sessió. Aquí es guarda la informació relativa a la sessió de l'usuari
 * 
 * @author Jaume
 */
@Component
@SessionScope
public class SessionBean extends BaseBean {
	
	private List<IdiomaEnum> idiomas = new ArrayList<>(Arrays.asList(IdiomaEnum.values()));
	
	private IdiomaEnum idiomaSelected;
	
	private Usuario usuario;
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	@Autowired
	IUsuarioService usuarioService;
	
	@PostConstruct
	public void init() {
		locale = ((MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLocale();
		idiomaSelected = IdiomaEnum.getIdiomaEnumByLocale(locale);
		
		redireccionRolConsulta();
	}
	
	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		idiomaSelected = (IdiomaEnum)e.getNewValue();
		locale = idiomaSelected.getLocale();
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
		for (GrantedAuthority gAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			if (roles != "") {
				roles += ", ";
			}
			
			RolEnum rolEnum = RolEnum.valueOf(gAuthority.getAuthority());
			
			roles += getMessage(rolEnum.getMessage());
			
		}
		
		return roles;
		
	}
	
	
	private boolean hadRole(RolEnum rol) {
		boolean hadRole = false;
		
		if (rol != null) {
			String rolString = rol.toString();
			String rolStringSpring = "ROLE_" + rol.toString();
			for (GrantedAuthority gAuth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
				if (rolString.equalsIgnoreCase(gAuth.getAuthority()) || rolStringSpring.equalsIgnoreCase(gAuth.getAuthority())) {
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
	
	public boolean isOnlyConsultor() {
		return hadRole(RolEnum.CONSULTOR);
	}
	
	public boolean isAdminSis() {
		return hadRole(RolEnum.ADMIN_SIS);
	}
	
	public void redireccionRolConsulta() {
		if (isOnlyConsultor()) {
			redirectTo("/consulta.xhtml?personaId=" + getUsuario().getUsuari());
		}
		
	}
	
	private void redirectTo(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	
	public Locale getLocale() {
		return locale;
	}
	
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	
}
