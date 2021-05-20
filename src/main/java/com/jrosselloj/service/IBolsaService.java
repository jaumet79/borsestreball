package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.exceptions.BorsesTreballException;
import com.jrosselloj.model.Bolsa;
import com.jrosselloj.model.Criterio;
import com.jrosselloj.model.Merito;
import com.jrosselloj.model.Persona;
import com.jrosselloj.model.Seleccion;
import com.jrosselloj.model.Usuario;

public interface IBolsaService {
	
	void saveBolsa(Bolsa bolsa, Usuario usuarioCreador) throws BorsesTreballException;
	
	List<Bolsa> findAll();
	
	Bolsa findById(Integer id);
	
	List<Seleccion> listaPersonasByBolsa(Bolsa bolsa);
	
	void addPersonaToBolsa(Persona persona, Bolsa bolsa);
	
	void deletePersonaFromBolsa(Seleccion seleccion);
	
	List<Merito> listaMeritosBySeleccion(Seleccion seleccion);
	
	//	void saveMeritosToSeleccion(List<Merito> meritos);
	
	void excluirDeSeleccion(Seleccion seleccion);
	
	void anularExclusionEnSeleccion(Seleccion seleccion);
	
	boolean existeMeritConEsteCriterio(Criterio criterio);
	
	void saveMeritosToSeleccion(List<Merito> meritos, Seleccion seleccion);
	
	void eliminarBolsa(Integer bolsaId) throws BorsesTreballException;
	
}
