package com.jrosselloj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrosselloj.model.Categoria;
import com.jrosselloj.repository.ICategoriaRepo;

/**
 * Implementació de serveis de negoci de categories
 * 
 * @author Jaume
 */
@Service
public class CategoriaServiceImpl implements ICategoriaService {
	
	@Autowired
	private ICategoriaRepo categoriaRepo;
	
	@Override
	public void registrar(Categoria categoria) {
		categoriaRepo.save(categoria);
		
	}
	
	@Override
	public List<Categoria> findAll() {
		return categoriaRepo.findAllByOrderByDescripcionAsc();
	}
	
	@Override
	public Categoria findById(Integer idCategoria) {
		Optional<Categoria> categoria = categoriaRepo.findById(idCategoria);
		
		return categoria.isPresent() ? categoria.get() : null;
	}
	
	@Override
	public void eliminar(Categoria categoria) {
		categoriaRepo.delete(categoria);
		
	}
	
	@Override
	public boolean existeCategoria(String descripcion) {
		List<Categoria> categorias = categoriaRepo.findAllByDescripcion(descripcion);
		
		return categorias != null && categorias.size() > 0;
		
		
	}
	
}
