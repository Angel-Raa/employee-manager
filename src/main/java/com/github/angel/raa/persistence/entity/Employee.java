/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.raa.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

/**
 *
 * @author aguero
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
public class Employee implements Serializable {
    private static final long serialVersionUID = 2018316391;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, unique = true)
    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "El email no es valido")
    private String email;
    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(length = 50)
    private String nombre;
    @Column(length = 50)
    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;
    @NotBlank(message = "El cargo no puede estar vacio")
    @Column(length = 50)
    private String cargo;
    @NotNull(message = "El salario no puede ser nulo")
    @Positive(message = "El salario debe ser un valor positivo")
    @Digits(integer = 10, fraction = 2, message = "El salario debe tener como máximo 10 dígitos enteros y 2 fraccionarios")
    private long salario;
    @NotBlank(message = "El departamento no puede estar vacio")
    @Column(length = 50)
    private String departamento;
    @NotBlank(message = "El telefono no puede estar vacio")
    @Column(length = 20)
    private String telefono;
    @NotNull(message = "La fecha no puede ser nula")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha; // Cambiado de Date a LocalDate

}
