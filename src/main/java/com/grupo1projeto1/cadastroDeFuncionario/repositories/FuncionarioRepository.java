package com.grupo1projeto1.cadastroDeFuncionario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
}