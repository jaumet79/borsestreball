package com.jrosselloj.enums;

import java.util.ArrayList;
import java.util.List;


public enum RolEnum {
	
	ADMIN("rol.administrador"),
	EDITOR("rol.editor"),
	CONSULTOR("rol.consultor"),
	
	ADMIN_SIS("rol.admin.sis");
	
	private String message;
	
	private RolEnum(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public static List<RolEnum> getRolsPantallaUsuarios() {
		List<RolEnum> rolsPantallaUsuarios = new ArrayList<>();
		rolsPantallaUsuarios.add(ADMIN);
		rolsPantallaUsuarios.add(EDITOR);
		return rolsPantallaUsuarios;
	}
	
	
}
