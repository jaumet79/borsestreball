package com.jrosselloj.service;

import java.util.List;
import java.util.Optional;

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
	public void registrar(Usuario usuario, boolean encriptarPwd) {
		
		usuario.setUsuari(usuario.getUsuari().trim());
		usuario.setPassword(usuario.getPassword().trim());
		
		if (encriptarPwd) {
			usuario.setPassword(bcrypt.encode(usuario.getPassword()));
		}
		
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
	
	@Override
	public void cambiarContrasenya(String usuario, String nuevoPwd) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(usuario);
		if (usuarioOpt.isPresent()) {
			Usuario usu = usuarioOpt.get();
			usu.setPassword(nuevoPwd);
			registrar(usu, true);
		}
		
	}
	
}
