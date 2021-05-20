package com.jrosselloj.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;

import com.jrosselloj.enums.EstatSeleccio;
import com.jrosselloj.enums.FaseBorsa;

@Entity
public class Bolsa {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String descripcio;
	
	@Column(nullable = false, length = 15)
	@Enumerated(EnumType.STRING)
	private FaseBorsa fase = FaseBorsa.SOLICITUD;
	
	private Date dataPublicacio;
	
	private Date dataInici;
	
	private Date dataSistema;
	
	@OneToMany(mappedBy = "bolsa", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(clause = "tipo ASC")
	private List<Criterio> criterios;
	
	@ManyToOne
	@JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "usuariCreador", referencedColumnName = "usuari", nullable = false)
	private Usuario usuariCreador;
	
	@OneToMany(mappedBy = "bolsa")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OrderBy(clause = "estat, puntuacionTotal DESC")
	private List<Seleccion> listaSeleccion;
	
	
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
	
	public FaseBorsa getFase() {
		return fase;
	}
	
	public void setFase(FaseBorsa fase) {
		this.fase = fase;
	}
	
	public List<Seleccion> getListaSeleccion() {
		if (listaSeleccion != null) {
			
			int orden = 1;
			
			for (Seleccion seleccion : listaSeleccion) {
				if (EstatSeleccio.ADMES.equals(seleccion.getEstat())) {
					seleccion.setOrden(orden++);
				}
			}
		}
		
		return listaSeleccion;
		
	}
	
	
	
}
