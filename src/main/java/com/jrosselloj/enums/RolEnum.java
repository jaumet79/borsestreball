package com.jrosselloj.enums;


public enum RolEnum {
	
	ADMIN("Administrador"),
	EDITOR("Editor"),
	CONSULTOR("Consultor");
	
	private String descripcion;
	
	private RolEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
}
