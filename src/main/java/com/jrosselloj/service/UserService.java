package com.jrosselloj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jrosselloj.model.Usuario;
import com.jrosselloj.repository.IUsuarioRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = usuarioRepo.findByUsuari(username);
		
		if (us != null) {
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(us.getRol().toString()));
			
			UserDetails userDet = new User(us.getUsuari(), us.getPassword(), roles);
			
			return userDet;
		}
		throw new UsernameNotFoundException("ddd");
		
	}
	
}
