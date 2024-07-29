/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.raa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author aguero
 */
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.angel.raa.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/home")
public class HomeController {
    private final EmployeeService service;


    @GetMapping
    public String index(final Model model) {
        model.addAttribute("title", "Gestor de Empleados");
        model.addAttribute("message", "Bienvenido a nuestro sistema");
        model.addAttribute("count", service.count());
        model.addAttribute("countDepartamento", service.countDistinctDepartamentos());
        return "home";
    }

}
