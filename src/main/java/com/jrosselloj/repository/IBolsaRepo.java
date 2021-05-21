package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Bolsa;

/**
 * Clase accés a la taula Bolsa
 * 
 * @author Jaume
 */
public interface IBolsaRepo extends JpaRepository<Bolsa, Integer> {
	
	
}
