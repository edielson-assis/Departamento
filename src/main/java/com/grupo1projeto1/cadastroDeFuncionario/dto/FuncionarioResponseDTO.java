package com.grupo1projeto1.cadastroDeFuncionario.dto;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Funcionario;

import lombok.*;

@NoArgsConstructor
@Data
public class FuncionarioResponseDTO {
    
    private Long id;
    private String nome;
    private Cargo cargo;
    private Departamento departamento;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
        this.departamento = funcionario.getDepartamento();
    }
}