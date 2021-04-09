package com.jrosselloj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrosselloj.model.Usuario;
import com.jrosselloj.repository.IUsuarioRepo;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Override
	public void registrar(Usuario usuario) {
		usuarioRepo.save(usuario);
		
	}
	
	@Override
	public List<Usuario> findAll() {
		return usuarioRepo.findAll();
	}
	
}
