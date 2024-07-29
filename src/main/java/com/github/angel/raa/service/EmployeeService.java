/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.raa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.angel.raa.exception.EmployeeNotFoundException;
import com.github.angel.raa.persistence.entity.Employee;

/**
 *
 * @author aguero
 */
public interface EmployeeService {
    List<Employee> findAll();

    Page<Employee> findAll(Pageable pageable);

    Employee findById(Long id) throws EmployeeNotFoundException;

    void save(Employee employee);

    void deleteById(Long id);

    void update(Employee employee, Long id);

    Employee findByEmail(String email) throws EmployeeNotFoundException;

    long count();

    long countDistinctDepartamentos();

}
