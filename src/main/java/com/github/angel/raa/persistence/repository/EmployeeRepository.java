/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.raa.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.angel.raa.persistence.entity.Employee;

/**
 *
 * @author aguero
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT COUNT(DISTINCT e.departamento) FROM Employee e")
    long countDistinctDepartamentos();
    


}
