package com.jrosselloj.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.model.Persona;
import com.jrosselloj.service.IPersonaService;

@Component
@Scope("view")
public class PersonaBean {
	
	@Autowired
	private IPersonaService personaService;
	
	private Persona persona = new Persona();
	
	private List<IdiomaEnum> idiomas = new ArrayList<>(Arrays.asList(IdiomaEnum.values()));
	
	private List<Persona> persones;
	
	@PostConstruct
	public void init() {
		carregaPersones();
	}
	
	private void carregaPersones() {
		persones = personaService.findAll();
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public void guardarPersona() {
		personaService.registrar(persona);
		persona = new Persona();
		carregaPersones();
	}
	
	public void cargarModificacionPersona() {
	}
	
	public List<IdiomaEnum> getIdiomas() {
		return idiomas;
	}
	
	
	public List<Persona> getPersones() {
		return persones;
	}
	
	
	
}
