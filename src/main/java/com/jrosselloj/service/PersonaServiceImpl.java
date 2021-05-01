package com.jrosselloj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrosselloj.model.Persona;
import com.jrosselloj.repository.IPersonaRepo;
import com.jrosselloj.repository.ISeleccionRepo;

@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersonaRepo personaRepo;
	
	@Autowired
	private ISeleccionRepo seleccionRepo;
	
	@Override
	public void registrar(Persona persona) {
		personaRepo.save(persona);
		
	}
	
	@Override
	public List<Persona> findAll() {
		return personaRepo.findAll();
	}
	
	@Override
	public Persona findByDni(String dni) {
		Optional<Persona> persona = personaRepo.findById(dni);
		
		return persona.isPresent() ? persona.get() : null;
	}
	
}
