package com.example.demo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

    public class NotFoundException extends RuntimeException{};

    @ExceptionHandler(NotFoundException.class)            //obsługa wyjątków
    public String notFound(){
        return "404";
    }

    @ExceptionHandler(NumberFormatException.class)            //obsługa wyjątków
    public String wrongFormatID(){
        return "numb-format-exep";
    }

}
