package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Bolsa;
import com.jrosselloj.model.Merito;
import com.jrosselloj.model.Persona;
import com.jrosselloj.model.Seleccion;
import com.jrosselloj.model.Usuario;

public interface IBolsaService {
	
	void saveBolsa(Bolsa bolsa, Usuario usuarioCreador);
	
	List<Bolsa> findAll();
	
	Bolsa findById(Integer id);
	
	List<Seleccion> listaPersonasByBolsa(Bolsa bolsa);
	
	void addPersonaToBolsa(Persona persona, Bolsa bolsa);
	
	List<Merito> listaMeritosBySeleccion(Seleccion seleccion);
	
	void saveMeritosToSeleccion(List<Merito> meritos);
	
	void excluirDeSeleccion(Seleccion seleccion);
	
	void anularExclusionEnSeleccion(Seleccion seleccion);
	
}
