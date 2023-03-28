package com.grupo1projeto1.cadastroDeFuncionario.entities;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departamento")
@Entity
public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento")
    private final List<Funcionario> funcionarios = new ArrayList<>();
}