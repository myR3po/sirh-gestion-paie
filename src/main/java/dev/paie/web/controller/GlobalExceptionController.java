package dev.paie.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.exception.BulletinSalaireNotFoundException;

@ControllerAdvice
public class GlobalExceptionController {
	
//  public static final Logger LOG = Logger.getLogger(CustomAccessDeniedHandler.class);
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(Exception ex, HttpServletResponse resp) {

//      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//      if (auth != null) {
//          LOG.warn("User: " + auth.getName() 
//            + " attempted to access the protected URL: "
//            + request.getRequestURI());
//      }
		
		resp.setStatus(403);
		return "redirect:/mvc/notAuthorize";
	}
	
	@ExceptionHandler(BulletinSalaireNotFoundException.class)
	public ModelAndView handleBulletinSalaireNotFoundException(Exception ex, HttpServletResponse resp) {

		resp.setStatus(404);
		ModelAndView model = new ModelAndView("errors/error404");
		return model;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView Exception(Exception ex, HttpServletResponse resp) {

		resp.setStatus(404);
		ModelAndView model = new ModelAndView("errors/errorDefault");
		return model;
	}
	
	

}
