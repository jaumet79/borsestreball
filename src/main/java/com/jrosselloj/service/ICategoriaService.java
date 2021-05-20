package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Categoria;

public interface ICategoriaService {
	
	void registrar(Categoria categoria);
	
	List<Categoria> findAll();
	
	Categoria findById(Integer idCategoria);
	
	void eliminar(Categoria categoria);
	
	boolean existeCategoria(String descripcion);
	
}
