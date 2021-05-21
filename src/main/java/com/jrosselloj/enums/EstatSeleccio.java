package com.jrosselloj.enums;


/**
 * Enum estat de la selecció
 * 
 * @author Jaume
 */
public enum EstatSeleccio {
	
	ADMES("estado.admitido"),
	EXCLOS("estado.excluido");
	
	private String message;
	
	private EstatSeleccio(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	
}
