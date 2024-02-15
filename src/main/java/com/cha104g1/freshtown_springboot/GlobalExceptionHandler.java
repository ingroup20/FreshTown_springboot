package com.cha104g1.freshtown_springboot;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllExceptions(Exception e) {

		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", e.getMessage());
		modelAndView.setViewName("excep");
		
		return modelAndView;
		
	}
	
	/*
	 @ExceptionHandler(Exception.class)
	 public String handleAllExceptions(Exception e, Model model){
	 
	    model.addAttribute("msg", e.getMessage());
	    return "excep";
	 }
	 
	 */

}
