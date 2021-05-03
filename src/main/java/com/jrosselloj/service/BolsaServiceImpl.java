package com.jrosselloj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jrosselloj.enums.EstatSeleccio;
import com.jrosselloj.model.Bolsa;
import com.jrosselloj.model.Criterio;
import com.jrosselloj.model.Merito;
import com.jrosselloj.model.Persona;
import com.jrosselloj.model.Seleccion;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.repository.IBolsaRepo;
import com.jrosselloj.repository.IMeritoRepo;
import com.jrosselloj.repository.ISeleccionRepo;

@Service
public class BolsaServiceImpl implements IBolsaService {
	
	@Autowired
	private IBolsaRepo bolsaRepo;
	
	@Autowired
	private ISeleccionRepo seleccionRepo;
	
	@Autowired
	private IMeritoRepo meritoRepo;
	
	@Override
	public List<Bolsa> findAll() {
		return bolsaRepo.findAll();
	}
	
	
	@Override
	public void saveBolsa(Bolsa bolsa, Usuario usuarioCreador) {
		
		if (bolsa.getId() == null) {
			bolsa.setDataSistema(new Date());
			bolsa.setUsuariCreador(usuarioCreador);
		}
		
		bolsaRepo.save(bolsa);
		
	}
	
	
	@Override
	public Bolsa findById(Integer id) {
		Optional<Bolsa> bolsa = bolsaRepo.findById(id);
		
		return bolsa.isPresent() ? bolsa.get() : null;
		
	}
	
	
	@Override
	public List<Seleccion> listaPersonasByBolsa(Bolsa bolsa) {
		Seleccion seleccion = new Seleccion();
		seleccion.setBolsa(bolsa);
		
		Example<Seleccion> example = Example.of(seleccion);
		return seleccionRepo.findAll(example);
	}
	
	@Override
	public void addPersonaToBolsa(Persona persona, Bolsa bolsa) {
		
		Seleccion seleccion = new Seleccion();
		//seleccion.setPersona(persona);
		seleccion.setPersonaDni(persona.getDni());
		//seleccion.setBolsa(bolsa);
		seleccion.setBolsaId(bolsa.getId());
		seleccion.setEstat(EstatSeleccio.ADMES);
		
		seleccionRepo.save(seleccion);
		
	}
	
	
	@Override
	public List<Merito> listaMeritosBySeleccion(Seleccion seleccion) {
		
		List<Criterio> criterios = seleccion.getBolsa().getCriterios();
		List<Merito> meritos = obtenerMeritosBySeleccion(seleccion);
		
		// Se añaden los meritos que la seleccion no tenga
		if (criterios != null) {
			boolean existe = false;
			for (Criterio criterio : criterios) {
				for (Merito merito : meritos) {
					if (merito.getCriterio().getId().equals(criterio.getId())) {
						existe = true;
					}
				}
				
				if (!existe) {
					Merito merito = new Merito();
					merito.setSeleccion(seleccion);
					merito.setCriterio(criterio);
					merito.setCriterioId(criterio.getId());
					merito.setBolsaId(seleccion.getBolsaId());
					merito.setPersonaDni(seleccion.getPersonaDni());
					meritos.add(merito);
				}
			}
		}
		
		return meritos;
		
	}
	
	
	private List<Merito> obtenerMeritosBySeleccion(Seleccion seleccion) {
		Merito meritoFind = new Merito();
		Seleccion seleccionFind = new Seleccion();
		seleccionFind.setPersonaDni(seleccion.getPersonaDni());
		seleccionFind.setBolsaId(seleccion.getBolsaId());
		meritoFind.setSeleccion(seleccionFind);
		Example<Merito> example = Example.of(meritoFind);
		List<Merito> meritos = meritoRepo.findAll(example);
		
		if (meritos == null) {
			meritos = new ArrayList<>();
		}
		return meritos;
	}
	
	
	@Override
	public void saveMeritosToSeleccion(List<Merito> meritos) {
		meritoRepo.saveAll(meritos);
		
		// Es calcula el total de puntuacio
		Integer totalPuntos = calcularPuntuacionPorMeritos(meritos);
		
		Seleccion seleccion = meritos.get(0).getSeleccion();
		seleccion.setPuntuacionTotal(totalPuntos);
		
		seleccionRepo.save(seleccion);
		
	}
	
	
	private Integer calcularPuntuacionPorMeritos(List<Merito> meritos) {
		Integer totalPuntos = 0;
		for (Merito merito : meritos) {
			
			Integer unidades = merito.getUnidades() != null ? merito.getUnidades() : 0;
			
			Integer puntos = unidades * merito.getCriterio().getPuntosPorUnidad();
			
			if (puntos > merito.getCriterio().getPuntuacionMaxima()) {
				puntos = merito.getCriterio().getPuntuacionMaxima();
			}
			
			totalPuntos += puntos;
		}
		return totalPuntos;
	}
	
	
	@Override
	public void excluirDeSeleccion(Seleccion seleccion) {
		seleccion.setEstat(EstatSeleccio.EXCLOS);
		seleccion.setPuntuacionTotal(0);
		seleccionRepo.save(seleccion);
		
	}
	
	@Override
	public void anularExclusionEnSeleccion(Seleccion seleccion) {
		seleccion.setEstat(EstatSeleccio.ADMES);
		seleccion.setMotiuExclusio(null);
		
		List<Merito> meritos = obtenerMeritosBySeleccion(seleccion);
		seleccion.setPuntuacionTotal(calcularPuntuacionPorMeritos(meritos));
		
		seleccionRepo.save(seleccion);
		
	}
	
}
