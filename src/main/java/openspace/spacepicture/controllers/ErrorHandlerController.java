package openspace.spacepicture.controllers;

import openspace.spacepicture.exceptions.BadDateRangeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.time.DateTimeException;

@ControllerAdvice
public class ErrorHandlerController
{
    @ExceptionHandler(BadDateRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleBadDate(BadDateRangeException e){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("error",e);
        modelAndView.setViewName("errors/400error");

        return modelAndView;
    }

    @ExceptionHandler(DateTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleNotSpecifiedDate(DateTimeException e){

        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("error",e);
        modelAndView.setViewName("errors/400error");

        return modelAndView;
    }
}
