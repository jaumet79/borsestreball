package com.jrosselloj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jrosselloj.MyUserDetails;
import com.jrosselloj.enums.IdiomaEnum;
import com.jrosselloj.model.Usuario;
import com.jrosselloj.repository.IUsuarioRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUserDetails userDet = null;
		
		if ("ADMIN_SIS".equals(username)) {
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority("ADMIN_SIS"));
			userDet = new MyUserDetails(username, bcrypt.encode("1234"), roles, IdiomaEnum.CATALA.getLocale());
			
		} else {
			Usuario us = usuarioRepo.findByUsuari(username);
			
			if (us != null && us.getActivo() != null && us.getActivo()) {
				List<GrantedAuthority> roles = new ArrayList<>();
				roles.add(new SimpleGrantedAuthority(us.getRol().toString()));
				
				Locale locale = null;
				if (us.getIdiomaDefecte() != null) {
					locale = us.getIdiomaDefecte().getLocale();
				}
				
				userDet = new MyUserDetails(us.getUsuari(), us.getPassword(), roles, locale);
				
			}
		}
		
		if (userDet != null) {
			return userDet;
		}
		
		throw new UsernameNotFoundException("No s'ha trobat usuari a la base de dades");
		
	}
	
}
