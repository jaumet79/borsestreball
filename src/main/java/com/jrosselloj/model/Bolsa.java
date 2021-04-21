package com.jrosselloj.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bolsa {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String descripcio;
	
	private Date dataPublicacio;
	
	private Date dataInici;
	
	private Date dataSistema;
	
	@OneToMany(mappedBy = "bolsa", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	List<Criterio> criterios;
	
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
	
	
	public Date getDataPublicacio() {
		return dataPublicacio;
	}
	
	
	public void setDataPublicacio(Date dataPublicacio) {
		this.dataPublicacio = dataPublicacio;
	}
	
	
	public Date getDataInici() {
		return dataInici;
	}
	
	
	public void setDataInici(Date dataInici) {
		this.dataInici = dataInici;
	}
	
	
	public Date getDataSistema() {
		return dataSistema;
	}
	
	
	public void setDataSistema(Date dataSistema) {
		this.dataSistema = dataSistema;
	}
	
	
	
	public List<Criterio> getCriterios() {
		if (criterios == null) {
			criterios = new ArrayList<>();
		}
		return criterios;
	}
	
	
}
