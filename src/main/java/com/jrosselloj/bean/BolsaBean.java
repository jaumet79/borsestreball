package com.jrosselloj.bean;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jrosselloj.enums.FaseBorsa;
import com.jrosselloj.enums.TipoCriterio;
import com.jrosselloj.exceptions.BorsesTreballException;
import com.jrosselloj.model.Bolsa;
import com.jrosselloj.model.Categoria;
import com.jrosselloj.model.Criterio;
import com.jrosselloj.service.IBolsaService;
import com.jrosselloj.service.ICategoriaService;

/**
 * Classe controladora de la vista de bolsa.xhtml i index.xhtml
 * 
 * @author Jaume
 */
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
	private List<FaseBorsa> fasesBorsa = Arrays.asList(FaseBorsa.values());
	
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
		
		for (Criterio crit : bolsa.getCriterios()) {
			if (crit.getDescripcio() == null
					|| crit.getTipo() == null
					|| crit.getPuntosPorUnidad() == null
					|| crit.getPuntuacionMaxima() == null) {
				showError(getMessage("bolsas.msg.no.anyadir.criterios"));
				return;
			}
			
		}
		
		Criterio c = new Criterio();
		c.setBolsa(bolsa);
		bolsa.getCriterios().add(c);
	}
	
	public TipoCriterio[] getTipoCriterioList() {
		return TipoCriterio.values();
	}
	
	public void removeCriterio(Criterio criterio) {
		
		if (bolsaService.existeMeritConEsteCriterio(criterio)) {
			showError(getMessage("bolsas.msg.no.eliminar.crit"));
			
		} else {
			
			bolsa.getCriterios().remove(criterio);
			
			if (bolsa.getCriterios().size() == 0) {
				bolsa.getCriterios().clear();
			}
		}
	}
	
	public void saveBolsa() {
		try {
			bolsaService.saveBolsa(bolsa, getUsuario());
			showInfo(getMessage("bolsas.msg.guardar.ok"));
			
		} catch (BorsesTreballException e) {
			showError(getMessage(e.getMessage()));
			
		} catch (Exception e) {
			showError(getMessage("bolsas.msg.guardar.ko"));
			
		}
		
	}
	
	public void eliminarBolsa(Integer bolsaId) {
		try {
			bolsaService.eliminarBolsa(bolsaId);
			loadAll();
			
			showInfo(getMessage("bolsas.msg.eliminar.ok"));
			
		} catch (BorsesTreballException e) {
			showError(getMessage(e.getMessage()));
			
		} catch (Exception e) {
			showError(getMessage("bolsas.msg.eliminar.except"));
			
		}
	}
	
	
	public List<Bolsa> getBolsas() {
		return bolsas;
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
	
	public boolean isHayCategorias() {
		return categorias != null && categorias.size() > 0;
	}
	
	public List<FaseBorsa> getFasesBorsa() {
		return fasesBorsa;
	}
	
	
}
