package dev.paie.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.exception.BulletinSalaireNotFoundException;

@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(BulletinSalaireNotFoundException.class)
	public ModelAndView handleBulletinSalaireNotFoundException(Exception ex) {

		ModelAndView model = new ModelAndView("errors/error404");
		return model;
	}

}
