/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.raa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.angel.raa.persistence.entity.Employee;
import com.github.angel.raa.service.EmployeeService;
import com.github.angel.raa.utils.PageRenderUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String findAllPages(Model model,
            @RequestParam(name = "pages", defaultValue = "0") int pages) {
        PageRequest pageable = PageRequest.of(pages, 5);
        Page<Employee> employee = employeeService.findAll(pageable);
        PageRenderUtils<Employee> pageRenderUtils = new PageRenderUtils<>("/", employee);
        model.addAttribute("title", "Employee Manager");
        model.addAttribute("pageRenderUtils", pageRenderUtils); // pages
        model.addAttribute("employees", employee);
        model.addAttribute("employee", new Employee());
        return "view";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, final Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("title", "Employee Details");
        model.addAttribute("employee", employee);
        return "details";
    }

    @PostMapping("/registro")
    public String saveEmployee(@Valid Employee employee, BindingResult result, Model model) {
        model.addAttribute("departamentos", List.of(
                "Ventas",
                "Marketing",
                "IT",
                "RRHH",
                "Finanzas",
                "Logística",
                "Producción",
                "Calidad",
                "Compras",
                "Atención al Cliente",
                "Investigación y Desarrollo",
                "Legal",
                "Administración",
                "Servicio Técnico",
                "Mantenimiento"));
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            model.addAttribute("errors", errors);
            model.addAttribute("employee", employee);
            return "form/registro";
        }

        model.addAttribute("employee", employee);
        employeeService.save(employee);
        return "redirect:/home";
    }

    @GetMapping("/registro")
    public String form(final Model model) {
        model.addAttribute("departamentos", List.of(
                "Ventas",
                "Marketing",
                "IT",
                "RRHH",
                "Finanzas",
                "Logística",
                "Producción",
                "Calidad",
                "Compras",
                "Atención al Cliente",
                "Investigación y Desarrollo",
                "Legal",
                "Administración",
                "Servicio Técnico",
                "Mantenimiento"));
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", "Registro de empleados");
        return "form/registro";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable @Min(1) Long id, @Valid @ModelAttribute final Employee employee,
            final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            model.addAttribute("errors", errors);
            model.addAttribute("employee", employee);
            return "edit";
        }
        Employee employeeDb = employeeService.findById(id);
        if (employeeDb == null) {
            return "error";
        }
        model.addAttribute("employee", employee);
        employeeService.update(employeeDb, id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, final Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("id", id);
        model.addAttribute("title", "Editar empleado");
        model.addAttribute("departamentos", List.of(
                "Ventas",
                "Marketing",
                "IT",
                "RRHH",
                "Finanzas",
                "Logística",
                "Producción",
                "Calidad",
                "Compras",
                "Atención al Cliente",
                "Investigación y Desarrollo",
                "Legal",
                "Administración",
                "Servicio Técnico",
                "Mantenimiento"));
        return "edit";
    }
}
