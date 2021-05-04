package com.jrosselloj.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jrosselloj.enums.MotiuExclusio;
import com.jrosselloj.model.Bolsa;
import com.jrosselloj.model.Merito;
import com.jrosselloj.model.Persona;
import com.jrosselloj.model.Seleccion;
import com.jrosselloj.service.IBolsaService;
import com.jrosselloj.service.IPersonaService;

@Component
@Scope("view")
public class SeleccionBean extends BaseBean {
	
	@Autowired
	IBolsaService bolsaService;
	
	@Autowired
	IPersonaService personaService;
	
	//	private List<Seleccion> personasByBolsa;
	private Bolsa bolsa;
	
	private String dni;
	private Persona personaBusqueda;
	
	private Seleccion seleccion;
	private List<Merito> meritos;
	
	String bolsaId;
	
	private List<MotiuExclusio> listaMotivosExclusion = new ArrayList<>(Arrays.asList(MotiuExclusio.values()));
	
	@PostConstruct
	public void init() {
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		bolsaId = request.getParameter("bolsaId");
		
		// TODO mostrar error si no viene bolsaId
		
		cargarBolsa();
	}
	
	private void cargarBolsa() {
		bolsa = bolsaService.findById(Integer.parseInt(bolsaId));
	}
	
	public void buscarPersonaPorDni() {
		personaBusqueda = personaService.findByDni(dni);
		
		if (personaBusqueda == null) {
			showInfo("No s'ha trobat cap persona amb aquest document");
		}
		
	}
	
	public void addPersonaToBolsa() {
		bolsaService.addPersonaToBolsa(personaBusqueda, bolsa);
		
		personaBusqueda = null;
		
		cargarBolsa();
		
	}
	
	public void openNew() {
		
	}
	
	//	public List<Seleccion> getPersonasByBolsa() {
	//		return personasByBolsa;
	//	}
	
	public void cargarMeritosBySeleccion() {
		meritos = bolsaService.listaMeritosBySeleccion(seleccion);
	}
	
	public void guardarMeritosToSeleccion() {
		bolsaService.saveMeritosToSeleccion(meritos);
		cargarBolsa();
		
	}
	
	public void excluirSeleccion() {
		bolsaService.excluirDeSeleccion(seleccion);
		cargarBolsa();
		
	}
	
	public void incluirSeleccion() {
		bolsaService.anularExclusionEnSeleccion(seleccion);
		cargarBolsa();
		
	}
	
	
	public Bolsa getBolsa() {
		return bolsa;
	}
	
	
	
	public String getDni() {
		return dni;
	}
	
	
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
	public Persona getPersonaBusqueda() {
		return personaBusqueda;
	}
	
	
	
	public Seleccion getSeleccion() {
		return seleccion;
	}
	
	
	
	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}
	
	
	
	public List<Merito> getMeritos() {
		return meritos;
	}
	
	
	public List<MotiuExclusio> getListaMotivosExclusion() {
		return listaMotivosExclusion;
	}
	
	
	
	
	
}
