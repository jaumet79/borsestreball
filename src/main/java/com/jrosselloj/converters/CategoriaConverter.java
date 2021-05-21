package com.jrosselloj.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jrosselloj.model.Categoria;
import com.jrosselloj.service.ICategoriaService;


/**
 * Classe converter per a convertir valors de primefaces per guardar a hibernate
 * 
 * @author Jaume
 */
@FacesConverter("categoriaConverter")
@Component("categoriaConverter")
public class CategoriaConverter implements Converter {
	
	@Autowired
	ICategoriaService categoriaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		return categoriaService.findById(Integer.parseInt(value));
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Categoria)value).getId().toString();
	}
	
}
