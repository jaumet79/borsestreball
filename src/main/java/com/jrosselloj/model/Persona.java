package com.jrosselloj.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.jrosselloj.enums.IdiomaEnum;

/**
 * Entitat persona
 * 
 * @author Jaume
 */
@Entity
public class Persona {
	
	@Id
	@Column(length = 9)
	private String dni;
	
	@Column(length = 30)
	private String nom;
	
	@Column(length = 30)
	private String cognom1;
	
	@Column(length = 30)
	private String cognom2;
	
	private Date dataNaixament;
	
	@Column(length = 9)
	private String telefon;
	
	@Enumerated(EnumType.STRING)
	private IdiomaEnum idioma;
	
	@OneToOne
	@JoinColumn(name = "usuariConsulta", referencedColumnName = "usuari")
	private Usuario usuariConsulta;
	
	@OneToMany(mappedBy = "personaDni")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Seleccion> selecciones;
	
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCognom1() {
		return cognom1;
	}
	
	public void setCognom1(String cognom1) {
		this.cognom1 = cognom1;
	}
	
	public String getCognom2() {
		return cognom2;
	}
	
	public void setCognom2(String cognom2) {
		this.cognom2 = cognom2;
	}
	
	public String getNomComplet() {
		return this.cognom1 + " "
				+ this.cognom2 + ", "
				+ this.nom;
	}
	
	public Date getDataNaixament() {
		return dataNaixament;
	}
	
	public void setDataNaixament(Date dataNaixament) {
		this.dataNaixament = dataNaixament;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public IdiomaEnum getIdioma() {
		return idioma;
	}
	
	public void setIdioma(IdiomaEnum idioma) {
		this.idioma = idioma;
	}
	
	public Usuario getUsuariConsulta() {
		return usuariConsulta;
	}
	
	public void setUsuariConsulta(Usuario usuariConsulta) {
		this.usuariConsulta = usuariConsulta;
	}
	
	
	public List<Seleccion> getSelecciones() {
		return selecciones;
	}
	
	
	
	
	
}
