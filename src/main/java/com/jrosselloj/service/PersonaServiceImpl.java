package com.jrosselloj.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrosselloj.enums.RolEnum;
import com.jrosselloj.model.Persona;
import com.jrosselloj.model.Seleccion;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.repository.IPersonaRepo;

@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersonaRepo personaRepo;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public void registrar(Persona persona) {
		persona = personaRepo.save(persona);
		
		// Cream usuari de consulta
		Usuario usuario = new Usuario();
		usuario.setUsuari(persona.getDni());
		usuario.setPassword(formatFecha(persona.getDataNaixament()));
		usuario.setRol(RolEnum.CONSULTOR);
		usuario.setIdiomaDefecte(persona.getIdioma());
		usuario.setPersona(persona);
		usuarioService.registrar(usuario);
		
	}
	
	private String formatFecha(Date fecha) {
		SimpleDateFormat objSDF = new SimpleDateFormat("dd/MM/yyyy");
		return objSDF.format(fecha);
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
	
	@Override
	public Persona findByDniAndInfoBolsas(String dni) {
		Persona persona = findByDni(dni);
		
		for (Seleccion seleccion : persona.getSelecciones()) {
			for (Seleccion seleccionBolsa : seleccion.getBolsa().getListaSeleccion()) {
				if (seleccion.getPersonaDni().equals(seleccionBolsa.getPersonaDni())) {
					seleccion.setOrdenConsultaPersona(seleccionBolsa.getOrden());
					break;
				}
			}
		}
		
		return persona;
	}
	
	
	
}
