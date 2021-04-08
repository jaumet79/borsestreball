package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Usuario;

public interface IUsuarioService {
	
	void registrar(Usuario usuario);
	
	List<Usuario> findAll();
	
	Usuario findByUsuari(String usuari);
	
}
