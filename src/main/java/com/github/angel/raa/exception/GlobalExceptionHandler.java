/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.raa.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author aguero
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public ModelAndView handleEmployeeNotFound(EmployeeNotFoundException exception){
        ModelAndView andView = new ModelAndView("error");
        andView.addObject("message", exception.getMessage());
        return andView;
    }

}
