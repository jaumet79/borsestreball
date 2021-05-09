package com.jrosselloj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.jrosselloj.enums.EstatSeleccio;
import com.jrosselloj.enums.MotiuExclusio;

@Entity
@IdClass(SeleccionKey.class)
public class Seleccion {
	
	@Id
	@Column(name = "bolsa")
	private Integer bolsaId;
	
	@Id
	@Column(name = "persona")
	private String personaDni;
	
	private Integer puntuacionTotal;
	
	@Enumerated(EnumType.STRING)
	private EstatSeleccio estat;
	
	@Enumerated(EnumType.STRING)
	private MotiuExclusio motiuExclusio;
	
	@ManyToOne
	@JoinColumn(name = "bolsa", referencedColumnName = "id", insertable = false, updatable = false)
	private Bolsa bolsa;
	
	@ManyToOne
	@JoinColumn(name = "persona", referencedColumnName = "dni", insertable = false, updatable = false)
	private Persona persona;
	
	@Transient
	private Integer orden;
	
	@Transient
	private Integer ordenConsultaPersona;
	
	public Integer getBolsaId() {
		return bolsaId;
	}
	
	
	
	public void setBolsaId(Integer bolsaId) {
		this.bolsaId = bolsaId;
	}
	
	
	
	public String getPersonaDni() {
		return personaDni;
	}
	
	
	
	public void setPersonaDni(String personaDni) {
		this.personaDni = personaDni;
	}
	
	
	public Bolsa getBolsa() {
		return bolsa;
	}
	
	
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	
	
	public Persona getPersona() {
		return persona;
	}
	
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	public Integer getPuntuacionTotal() {
		return puntuacionTotal;
	}
	
	
	public void setPuntuacionTotal(Integer puntuacionTotal) {
		this.puntuacionTotal = puntuacionTotal;
	}
	
	
	public EstatSeleccio getEstat() {
		return estat;
	}
	
	
	public void setEstat(EstatSeleccio estat) {
		this.estat = estat;
	}
	
	
	public MotiuExclusio getMotiuExclusio() {
		return motiuExclusio;
	}
	
	
	public void setMotiuExclusio(MotiuExclusio motiuExclusio) {
		this.motiuExclusio = motiuExclusio;
	}
	
	public Integer getOrden() {
		return orden;
	}
	
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	
	public Integer getOrdenConsultaPersona() {
		return ordenConsultaPersona;
	}
	
	
	public void setOrdenConsultaPersona(Integer ordenConsultaPersona) {
		this.ordenConsultaPersona = ordenConsultaPersona;
	}
	
	
}
