package com.jrosselloj.bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jrosselloj.model.Persona;
import com.jrosselloj.service.IPersonaService;

/**
 * Classe controladora de la vista de consulta.xhtml
 * 
 * @author Jaume
 */
@Component
@Scope("view")
public class ConsultaBean extends BaseBean {
	
	@Autowired
	private IPersonaService personaService;
	
	private Persona persona = new Persona();
	
	@PostConstruct
	public void init() {
		persona = personaService.findByDniAndInfoBolsas(getParametro("personaId"));
		
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	
}
