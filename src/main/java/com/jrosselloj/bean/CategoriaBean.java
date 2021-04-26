package com.jrosselloj.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jrosselloj.model.Categoria;
import com.jrosselloj.service.ICategoriaService;

@Component
@Scope("view")
public class CategoriaBean {
	
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
	}
	
	public void cargarModificacionCategoria() {
	}
	
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void eliminarCategoria() {
		categoriaService.eliminar(categoria);
		categoria = new Categoria();
		cargaCategorias();
	}
	
}
