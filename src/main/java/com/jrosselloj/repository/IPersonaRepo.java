package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Persona;

/**
 * Clase accés a la taula Persona
 * 
 * @author Jaume
 */
public interface IPersonaRepo extends JpaRepository<Persona, String> {
	
}
