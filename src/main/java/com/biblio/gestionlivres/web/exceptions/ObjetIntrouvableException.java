package com.biblio.gestionlivres.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Cette  classe permet la gestion des exception
 * 
 * @author atouli
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjetIntrouvableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetIntrouvableException(String s) {
        super(s);
    }
}
