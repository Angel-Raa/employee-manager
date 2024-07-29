/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.raa.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.angel.raa.exception.EmailAlreadyExistsException;
import com.github.angel.raa.exception.EmployeeNotFoundException;
import com.github.angel.raa.persistence.entity.Employee;
import com.github.angel.raa.persistence.repository.EmployeeRepository;
import com.github.angel.raa.service.EmployeeService;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author aguero
 */
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado"));
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        final String email = employee.getEmail();
        if (employeeRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("El email ya existe en la base de datos");
        }
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Employee employee = this.findById(id);
        employeeRepository.delete(employee);
    }

    @Transactional
    @Override
    public void update(Employee employee, Long id) {
        Employee employeeToUpdate = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado"));
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setApellido(employee.getApellido());
        employeeToUpdate.setNombre(employee.getNombre());
        employeeToUpdate.setCargo(employee.getCargo());
        employeeToUpdate.setSalario(employee.getSalario());
        employeeToUpdate.setFecha(employee.getFecha());
        employeeToUpdate.setTelefono(employee.getTelefono());
        employeeRepository.save(employeeToUpdate);

    }

    @Transactional(readOnly = true)
    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return employeeRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public long countDistinctDepartamentos() {
        return employeeRepository.countDistinctDepartamentos();
    }

}
