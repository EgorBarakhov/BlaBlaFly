package ru.kpfu.itis.barakhov.blablafly.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.barakhov.blablafly.exceptions.FlightNotFoundException;
import ru.kpfu.itis.barakhov.blablafly.exceptions.FlightSavingException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFound(HttpServletRequest httpServletRequest, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", httpServletRequest.getRequestURL());
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(FlightSavingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView couldNotSave(HttpServletRequest httpServletRequest, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", httpServletRequest.getRequestURL());
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
