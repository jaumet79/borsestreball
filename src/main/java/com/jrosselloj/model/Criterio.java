package com.jrosselloj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jrosselloj.enums.TipoCriterio;

@Entity
public class Criterio {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String descripcio;
	
	private TipoCriterio tipo;
	
	private Integer puntosPorUnidad;
	
	private Integer puntuacionMaxima;
	
	@ManyToOne
	@JoinColumn(name = "bolsa", referencedColumnName = "id", nullable = false)
	private Bolsa bolsa;
	
	public Integer getId() {
		return id;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getDescripcio() {
		return descripcio;
	}
	
	
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	
	public TipoCriterio getTipo() {
		return tipo;
	}
	
	
	public void setTipo(TipoCriterio tipo) {
		this.tipo = tipo;
	}
	
	
	public Integer getPuntosPorUnidad() {
		return puntosPorUnidad;
	}
	
	
	public void setPuntosPorUnidad(Integer puntosPorUnidad) {
		this.puntosPorUnidad = puntosPorUnidad;
	}
	
	public Bolsa getBolsa() {
		return bolsa;
	}
	
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	
	public Integer getPuntuacionMaxima() {
		return puntuacionMaxima;
	}
	
	public void setPuntuacionMaxima(Integer puntuacionMaxima) {
		this.puntuacionMaxima = puntuacionMaxima;
	}
	
}
