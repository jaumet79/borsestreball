package com.jrosselloj.enums;

import java.util.ArrayList;
import java.util.List;


public enum RolEnum {
	
	ADMIN("Administrador"),
	EDITOR("Editor"),
	CONSULTOR("Consultor"),
	
	ADMIN_SIS("Administrador sistema");
	
	private String descripcion;
	
	private RolEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public static List<RolEnum> getRolsPantallaUsuarios() {
		List<RolEnum> rolsPantallaUsuarios = new ArrayList<>();
		rolsPantallaUsuarios.add(ADMIN);
		rolsPantallaUsuarios.add(EDITOR);
		return rolsPantallaUsuarios;
	}
	
	
}
