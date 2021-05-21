package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Persona;

/**
 * Clase de serveis de negoci de persones candidates
 * 
 * @author Jaume
 */
public interface IPersonaService {
	
	void registrar(Persona persona);
	
	List<Persona> findAll();
	
	Persona findByDni(String dni);
	
	Persona findByDniAndInfoBolsas(String dni);
	
}
