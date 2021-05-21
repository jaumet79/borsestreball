package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Seleccion;
import com.jrosselloj.model.SeleccionKey;

/**
 * Clase accés a la taula Seleccion
 * 
 * @author Jaume
 */
public interface ISeleccionRepo extends JpaRepository<Seleccion, SeleccionKey> {
	
}
