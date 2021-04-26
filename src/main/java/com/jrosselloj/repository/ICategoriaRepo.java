package com.jrosselloj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrosselloj.model.Categoria;

public interface ICategoriaRepo extends JpaRepository<Categoria, Integer> {
	
	
}
