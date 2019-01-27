package com.ozguryazilim.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalException {

	
	 @ExceptionHandler()
	   public ModelAndView exception(Exception exception) {
		 
		 ModelAndView model= new ModelAndView();
		 model.setViewName("error");
		 model.addObject("data",exception.getMessage());
		 
		 return model;
		 
	      
	   }
	 
	 
}
