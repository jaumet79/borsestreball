package com.jrosselloj.enums;

public enum TipoCriterio {
	
	REQUISIT("Requisit"),
	MERIT("Merit");
	
	private String descripcion;
	
	private TipoCriterio(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
}
