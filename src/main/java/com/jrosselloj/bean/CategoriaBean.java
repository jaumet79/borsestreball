package com.jrosselloj.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.jrosselloj.model.Categoria;
import com.jrosselloj.service.ICategoriaService;

@Component
@Scope("view")
public class CategoriaBean extends BaseBean {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	private Categoria categoria = new Categoria();
	
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		cargaCategorias();
	}
	
	private void cargaCategorias() {
		categorias = categoriaService.findAll();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void guardarCategoria() {
		categoriaService.registrar(categoria);
		categoria = new Categoria();
		cargaCategorias();
		
		showInfo("Categoria guardada correctament");
	}
	
	public void cargarModificacionCategoria() {
	}
	
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void eliminarCategoria() {
		try {
			categoriaService.eliminar(categoria);
			categoria = new Categoria();
			cargaCategorias();
			
			showInfo("Categoria eliminada correctament");
			
		} catch (DataIntegrityViolationException e) {
			showError("No s'ha pogut eliminar degut a que hi ha alguna borsa amb aquesta categoria");
			
		}
		
		
		
	}
	
}
