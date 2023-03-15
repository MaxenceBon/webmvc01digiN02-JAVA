package fr.diginamic.webmvc01.controllers.advice;

import java.sql.SQLException;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @RestControllerAdvice
 * me permet "d'attraper" les erreurs d'exceptions JAVA
 * @author chris
 *
 */
@RestControllerAdvice
public class ErrorController {

	public ErrorController() {
		// TODO Auto-generated constructor stub
	}
	 

	 @ExceptionHandler(value = {Exception.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public String errorGeneralException(Exception e) {
	    String message = "Il y a une erreur : " + e.getMessage();
	    
	    return message;
	  }
	 	 
	
}
