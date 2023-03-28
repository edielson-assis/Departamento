package com.grupo1projeto1.cadastroDeFuncionario.dto;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
public class CargoDTO {
    
    private Long id;

    @NotEmpty(message = "O campo nao pode ser vazio")
    @NotNull(message = "O campo nao pode ser nulo.")
    private String nome;

    public CargoDTO(Cargo cargo) {
        this.id =  cargo.getId();
        this.nome = cargo.getNome();
    }
}