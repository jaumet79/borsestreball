package com.jrosselloj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jrosselloj.enums.EstatSeleccio;
import com.jrosselloj.enums.FaseBorsa;
import com.jrosselloj.exceptions.BorsesTreballException;
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
	public void saveBolsa(Bolsa bolsa, Usuario usuarioCreador) throws BorsesTreballException {
		
		// Comprovar si ja n'hi ha una amb la mateixa categoria
		// i amb fase solicitud, subsanar o meritos
		// no pot crear una nova
		
		Bolsa bolsaFind = new Bolsa();
		bolsaFind.setCategoria(bolsa.getCategoria());
		bolsaFind.setFase(null);
		Example<Bolsa> example = Example.of(bolsaFind);
		List<Bolsa> listBolsa = bolsaRepo.findAll(example);
		
		if (listBolsa != null) {
			for (Bolsa bolsaTmp : listBolsa) {
				if (!bolsaTmp.getId().equals(bolsa.getId())
						&& (FaseBorsa.SOLICITUD.equals(bolsaTmp.getFase())
								|| FaseBorsa.ESMENA.equals(bolsaTmp.getFase())
								|| FaseBorsa.MERITS.equals(bolsaTmp.getFase()))) {
					throw new BorsesTreballException("bolsas.msg.bolsas.abiertas");
				}
			}
		}
		
		// comprovar que no hi hagu un criteri null
		Criterio criterioAEliminar = null;
		for (Criterio crit : bolsa.getCriterios()) {
			if (crit.getDescripcio() == null
					|| crit.getTipo() == null
					|| crit.getPuntosPorUnidad() == null
					|| crit.getPuntuacionMaxima() == null) {
				criterioAEliminar = crit;
			}
			
		}
		if (criterioAEliminar != null) {
			bolsa.getCriterios().remove(criterioAEliminar);
		}
		
		if (bolsa.getId() == null) {
			bolsa.setDataSistema(new Date());
			bolsa.setUsuariCreador(usuarioCreador);
		}
		
		bolsa = bolsaRepo.save(bolsa);
		
		recalcularPuntosByBolsa(bolsa);
	}
	
	private void recalcularPuntosByBolsa(Bolsa bolsa) {
		if (bolsa.getListaSeleccion() != null) {
			for (Seleccion seleccion : bolsa.getListaSeleccion()) {
				List<Merito> meritos = obtenerMeritosBySeleccion(seleccion);
				saveMeritosToSeleccion(meritos, seleccion);
			}
		}
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
		seleccion.setBolsaId(bolsa.getId());
		seleccion.setPersonaDni(persona.getDni());
		seleccion.setPuntuacionTotal(0);
		seleccion.setEstat(EstatSeleccio.ADMES);
		
		seleccionRepo.save(seleccion);
		
	}
	
	@Override
	public void deletePersonaFromBolsa(Seleccion seleccion) {
		List<Merito> listaMeritos = obtenerMeritosBySeleccion(seleccion);
		
		meritoRepo.deleteAll(listaMeritos);
		
		seleccionRepo.delete(seleccion);
		
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
	public void saveMeritosToSeleccion(List<Merito> meritos, Seleccion seleccion) {
		meritoRepo.saveAll(meritos);
		
		// Es calcula el total de puntuacio
		Integer totalPuntos = calcularPuntuacionPorMeritos(meritos);
		
		//Seleccion seleccion = meritos.get(0).getSeleccion();
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
	
	@Override
	public boolean existeMeritConEsteCriterio(Criterio criterio) {
		Merito meritoFind = new Merito();
		meritoFind.setCriterio(criterio);
		Example<Merito> example = Example.of(meritoFind);
		List<Merito> meritos = meritoRepo.findAll(example);
		
		return meritos != null && meritos.size() > 0;
		
	}
	
	@Override
	public void eliminarBolsa(Integer bolsaId) throws BorsesTreballException {
		
		Optional<Bolsa> bolsaOpt = bolsaRepo.findById(bolsaId);
		Bolsa bolsa = null;
		if (bolsaOpt.isPresent()) {
			bolsa = bolsaOpt.get();
		}
		
		if (bolsa != null && bolsa.getListaSeleccion() != null && bolsa.getListaSeleccion().size() > 0) {
			throw new BorsesTreballException("bolsas.msg.eliminar.ko");
		}
		
		bolsaRepo.delete(bolsa);
		
	}
	
}
