package com.jrosselloj.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
public class BolsaBean {
	
	@Autowired
	private IBolsaService bolsaService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	private LanguageBean languageBean;
	
	private List<Bolsa> bolsas;
	
	private Bolsa bolsa = new Bolsa();
	
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		
		languageBean = (LanguageBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("languageBean");
		
		categorias = categoriaService.findAll();
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		String bolsaId = request.getParameter("bolsaId");
		
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
		FacesMessage msg = new FacesMessage("Criteri Editat", String.valueOf(event.getObject().getDescripcio()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent<Criterio> event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
		bolsaService.saveBolsa(bolsa, languageBean.getUsuario());
		
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
