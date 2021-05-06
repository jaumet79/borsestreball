package com.jrosselloj;

import java.util.Collection;
import java.util.Locale;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUserDetails extends User {
	
	private static final long serialVersionUID = 4945683374343655335L;
	
	private Locale locale;
	
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Locale locale) {
		super(username, password, authorities);
		this.locale = locale;
	}
	
	
	public Locale getLocale() {
		return locale;
	}
	
	
	
	
	
}
