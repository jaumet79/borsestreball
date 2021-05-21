package com.jrosselloj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Entitat mèrit
 * 
 * @author Jaume
 */
@Entity
@IdClass(MeritoKey.class)
public class Merito {
	
	@Id
	@Column(name = "bolsa")
	private Integer bolsaId;
	
	@Id
	@Column(name = "persona")
	private String personaDni;
	
	@Id
	@Column(name = "criterio")
	private Integer criterioId;
	
	private Integer unidades;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "bolsa", referencedColumnName = "bolsa", insertable = false, updatable = false),
			@JoinColumn(name = "persona", referencedColumnName = "persona", insertable = false, updatable = false)
	})
	private Seleccion seleccion;
	
	@ManyToOne
	@JoinColumn(name = "criterio", referencedColumnName = "id", insertable = false, updatable = false)
	private Criterio criterio;
	
	public Seleccion getSeleccion() {
		return seleccion;
	}
	
	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}
	
	public Integer getCriterioId() {
		return criterioId;
	}
	
	public void setCriterioId(Integer criterioId) {
		this.criterioId = criterioId;
	}
	
	public Criterio getCriterio() {
		return criterio;
	}
	
	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}
	
	public Integer getUnidades() {
		return unidades;
	}
	
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	
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
	
	public Integer getTotal() {
		Integer total = null;
		
		if (this.unidades != null) {
			total = this.unidades * this.criterio.getPuntosPorUnidad();
			total = total > this.criterio.getPuntuacionMaxima() ? this.criterio.getPuntuacionMaxima() : total;
			
		}
		
		return total;
	}
	
}
