package com.jrosselloj.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jrosselloj.enums.TipoCriterio;
import com.jrosselloj.model.Bolsa;
import com.jrosselloj.model.Categoria;
import com.jrosselloj.model.Criterio;
import com.jrosselloj.service.IBolsaService;
import com.jrosselloj.service.ICategoriaService;

@Component
@Scope("view")
public class BolsaBean extends BaseBean {
	
	@Autowired
	private IBolsaService bolsaService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	private List<Bolsa> bolsas;
	
	private Bolsa bolsa = new Bolsa();
	
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		
		categorias = categoriaService.findAll();
		
		String bolsaId = getParametro("bolsaId");
		
		if (StringUtils.hasLength(bolsaId)) {
			bolsa = bolsaService.findById(Integer.parseInt(bolsaId));
		}
		
		loadAll();
	}
	
	public void loadAll() {
		bolsas = bolsaService.findAll();
	}
	
	public void onAddNew() {
		Criterio c = new Criterio();
		c.setBolsa(bolsa);
		bolsa.getCriterios().add(c);
	}
	
	public TipoCriterio[] getTipoCriterioList() {
		return TipoCriterio.values();
	}
	
	public void onRowEdit(RowEditEvent<Criterio> event) {
		showInfo("S'ha editat el criteri", String.valueOf(event.getObject().getDescripcio()));
	}
	
	public void onRowCancel(RowEditEvent<Criterio> event) {
		showInfo("S'ha cancel·lat l'edició del criteri", String.valueOf(event.getObject().getId()));
	}
	
	public void removeCriterio(Criterio criterio) {
		bolsa.getCriterios().remove(criterio);
		
		if (bolsa.getCriterios().size() == 0) {
			bolsa.getCriterios().clear();
		}
	}
	
	// GET & SET -------------------------------
	
	public List<Bolsa> getBolsas() {
		return bolsas;
	}
	
	public void saveBolsa() {
		bolsaService.saveBolsa(bolsa, getUsuario());
		
	}
	
	
	public Bolsa getBolsa() {
		return bolsa;
	}
	
	
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	
	
	
	
}
