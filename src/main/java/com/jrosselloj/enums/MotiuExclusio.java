package com.jrosselloj.enums;


public enum MotiuExclusio {
	
	DNI_CADUCAT("exclusion.dni.caducado"),
	REQUISITS_INCORRECTES("exclusion.requisitos.mal");
	
	private String message;
	
	private MotiuExclusio(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
