package com.tienda.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // Creo una excepción personalizada para cuando se genere una consulta
												// sobre un recurso inexistente.
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;// Genero el ID para esta clase.

	public ResourceNotFoundException(String mensaje) {
		super(mensaje);
	}
}
