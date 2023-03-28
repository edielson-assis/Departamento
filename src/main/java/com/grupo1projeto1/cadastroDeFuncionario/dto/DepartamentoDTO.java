package com.grupo1projeto1.cadastroDeFuncionario.dto;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
public class DepartamentoDTO {
    
    private Long id;

    @NotEmpty(message = "O campo nao pode ser vazio")
    @NotNull(message = "O campo nao pode ser nulo.")
    private String nome;

    public DepartamentoDTO(Departamento departamento) {
        this.id = departamento.getId();
        this.nome = departamento.getNome();
    }
}