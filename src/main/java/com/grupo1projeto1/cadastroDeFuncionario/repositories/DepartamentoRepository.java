package com.grupo1projeto1.cadastroDeFuncionario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    List<Departamento> findByNome(String nome);
}