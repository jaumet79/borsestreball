package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Seleccion;
import com.jrosselloj.model.SeleccionKey;

public interface ISeleccionRepo extends JpaRepository<Seleccion, SeleccionKey> {
	
	//List<Seleccion> loadAllByBolsa(Bolsa bolsa);
}
