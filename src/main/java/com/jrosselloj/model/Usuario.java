package com.jrosselloj.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.enums.RolEnum;

@Entity
public class Usuario {
	
	@Id
	private String usuari;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RolEnum rol;
	
	@Enumerated(EnumType.STRING)
	private IdiomaEnum idiomaDefecte;
	
	public String getUsuari() {
		return usuari;
	}
	
	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public RolEnum getRol() {
		return rol;
	}
	
	public void setRol(RolEnum rol) {
		this.rol = rol;
	}
	
	
	public IdiomaEnum getIdiomaDefecte() {
		return idiomaDefecte;
	}
	
	
	public void setIdiomaDefecte(IdiomaEnum idiomaDefecte) {
		this.idiomaDefecte = idiomaDefecte;
	}
	
	
	
	
	
}
