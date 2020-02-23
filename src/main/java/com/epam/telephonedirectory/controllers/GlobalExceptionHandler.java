package com.epam.telephonedirectory.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleIOException(Exception ex) {
        ModelAndView errorMav = new ModelAndView();
        errorMav.addObject("exceptionName", ex.getClass().getSimpleName());
        errorMav.addObject("exceptionMessage", ex.getMessage());
        errorMav.setViewName("errorPage");

        return errorMav;

    }
}
