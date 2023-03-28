package com.grupo1projeto1.cadastroDeFuncionario.dto;

import java.math.BigDecimal;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FuncionarioUpdateDTO {
    
    @Size(min = 3, message = "'${validatedValue}' caracter minimo {min}.")
    @NotEmpty(message = "O campo nao pode ser vazio")
    @NotNull(message = "O campo nao pode ser nulo.")
    private String nome;

    @Size(min = 11, max = 14, message = "'${validatedValue}' caracter minimo {min}.")
    @NotEmpty(message = "O campo nao pode ser vazio")
    @NotNull(message = "O campo nao pode ser nulo.")
    private String telefone;

    @Size(min = 11, max = 14, message = "'${validatedValue}' caracter minimo {min}.")
    private String telefoneContato;

    @NotNull(message = "O campo nao pode ser nulo.")
    private BigDecimal salario;

    @NotNull(message = "O campo nao pode ser nulo.")
    private Cargo cargo;

    @NotNull(message = "O campo nao pode ser nulo.")
    private Departamento departamento;
}