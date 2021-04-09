package com.jrosselloj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrosselloj.model.Persona;
import com.jrosselloj.repository.IPersonaRepo;

@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersonaRepo personaRepo;
	
	@Override
	public void registrar(Persona persona) {
		personaRepo.save(persona);
		
	}
	
	@Override
	public List<Persona> findAll() {
		return personaRepo.findAll();
	}
	
}
