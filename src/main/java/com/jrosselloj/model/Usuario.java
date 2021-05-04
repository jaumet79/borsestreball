package com.jrosselloj.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.enums.RolEnum;

@Entity
public class Usuario {
	
	@Id
	@Column(length = 30)
	private String usuari;
	
	@Column(length = 100)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RolEnum rol;
	
	@Enumerated(EnumType.STRING)
	private IdiomaEnum idiomaDefecte;
	
	@OneToOne(mappedBy = "usuariConsulta")
	private Persona persona;
	
	@OneToMany(mappedBy = "usuariCreador")
	private List<Bolsa> bolsasCreadas;
	
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
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	public List<Bolsa> getBolsasCreadas() {
		return bolsasCreadas;
	}
	
}
