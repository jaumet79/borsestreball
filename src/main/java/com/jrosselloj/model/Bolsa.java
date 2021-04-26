package com.jrosselloj.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "usuariCreador", referencedColumnName = "usuari", nullable = false)
	private Usuario usuariCreador;
	
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
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Usuario getUsuariCreador() {
		return usuariCreador;
	}
	
	public void setUsuariCreador(Usuario usuariCreador) {
		this.usuariCreador = usuariCreador;
	}
	
}
