package com.grupo1projeto1.cadastroDeFuncionario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    
    List<Cargo> findByNome(String nome);
}