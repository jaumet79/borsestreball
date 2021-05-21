package com.jrosselloj.service;

import java.util.List;

import com.jrosselloj.model.Usuario;

/**
 * Clase de serveis de negoci d'usuaris
 * 
 * @author Jaume
 */
public interface IUsuarioService {
	
	void registrar(Usuario usuario, boolean encriptarPwd);
	
	List<Usuario> findAll();
	
	Usuario findById(String usuario);
	
	void cambiarContrasenya(String usuario, String nuevoPwd);
	
}
