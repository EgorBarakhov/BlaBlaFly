package ru.kpfu.itis.barakhov.blablafly.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.barakhov.blablafly.exceptions.AircraftSavingException;
import ru.kpfu.itis.barakhov.blablafly.exceptions.FlightNotFoundException;
import ru.kpfu.itis.barakhov.blablafly.exceptions.FlightSavingException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFound(HttpServletRequest httpServletRequest, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", httpServletRequest.getRequestURL());
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("error");
        LOG.warn("Catch a NOT_FOUND {} on url {}", exception.getMessage(), httpServletRequest.getRequestURL());
        return modelAndView;
    }

    @ExceptionHandler({FlightSavingException.class, AircraftSavingException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView couldNotSave(HttpServletRequest httpServletRequest, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", httpServletRequest.getRequestURL());
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("error");
        LOG.warn("Catch an INTERNAL_SERVER_ERROR {} on url {}", exception.getMessage(), httpServletRequest.getRequestURL());
        return modelAndView;
    }
}
