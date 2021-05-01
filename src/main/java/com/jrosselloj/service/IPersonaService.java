package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Persona;

public interface IPersonaService {
	
	void registrar(Persona persona);
	
	List<Persona> findAll();
	
	Persona findByDni(String dni);
	
}
