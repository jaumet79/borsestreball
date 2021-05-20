package com.jrosselloj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Categoria;

public interface ICategoriaRepo extends JpaRepository<Categoria, Integer> {
	
	public List<Categoria> findAllByOrderByDescripcionAsc();
	
	public List<Categoria> findAllByDescripcion(String descripcion);
	
}
