package com.grupo1projeto1.cadastroDeFuncionario.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grupo1projeto1.cadastroDeFuncionario.dto.FuncionarioDTO;
import com.grupo1projeto1.cadastroDeFuncionario.dto.FuncionarioResponseDTO;
import com.grupo1projeto1.cadastroDeFuncionario.dto.FuncionarioUpdateDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Funcionario;
import com.grupo1projeto1.cadastroDeFuncionario.services.ipml.FuncionarioServiceIpml;

import jakarta.validation.Valid;

@RequestMapping(value = "/funcionario")
@RestController
public class FuncionarioController {
    
    private final FuncionarioServiceIpml service;

    public FuncionarioController(FuncionarioServiceIpml service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<FuncionarioResponseDTO>> findAll() {
        List<Funcionario> funcionarios = service.findAll();
        List<FuncionarioResponseDTO> funcionarioResponseDTO = funcionarios.stream().map(FuncionarioResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(funcionarioResponseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioResponseDTO> findById(@PathVariable Long id) {
        Funcionario funcionario = service.findById(id);
        return ResponseEntity.ok().body(new FuncionarioResponseDTO(funcionario));
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = service.create(funcionarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody FuncionarioUpdateDTO funcionarioUpdateDTO, @PathVariable Long id) {
        service.update(id, funcionarioUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}