package com.jrosselloj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jrosselloj.model.Usuario;
import com.jrosselloj.repository.IUsuarioRepo;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public void registrar(Usuario usuario) {
		usuario.setPassword(bcrypt.encode(usuario.getPassword()));
		
		usuarioRepo.save(usuario);
		
	}
	
	@Override
	public List<Usuario> findAll() {
		return usuarioRepo.findAll();
	}
	
	@Override
	public Usuario findById(String usuario) {
		return usuarioRepo.findByUsuari(usuario);
	}
	
}
