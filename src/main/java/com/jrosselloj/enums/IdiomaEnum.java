package com.jrosselloj.enums;

import java.util.Locale;

/**
 * Idiomes de l'aplicació
 * 
 * @author Jaume
 */
public enum IdiomaEnum {
	
	CATALA("idioma.catalan", new Locale("ca", "ES")), //ca-ES
	CASTELLA("idioma.castellano", new Locale("es", "ES")); //es-ES
	
	private String message;
	private Locale locale;
	
	private IdiomaEnum(String message, Locale locale) {
		this.message = message;
		this.locale = locale;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public static IdiomaEnum getIdiomaEnumByLocale(Locale locale) {
		for (IdiomaEnum idiomaEnum : IdiomaEnum.values()) {
			if (idiomaEnum.getLocale().equals(locale)) {
				return idiomaEnum;
			}
		}
		return null;
	}
	
}
