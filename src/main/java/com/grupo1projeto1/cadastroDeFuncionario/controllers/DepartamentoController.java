package com.grupo1projeto1.cadastroDeFuncionario.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grupo1projeto1.cadastroDeFuncionario.dto.DepartamentoDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;
import com.grupo1projeto1.cadastroDeFuncionario.services.ipml.DepartamentoServiceIpml;

import jakarta.validation.Valid;

@RequestMapping(value = "/departamento")
@RestController
public class DepartamentoController {
    
    private final DepartamentoServiceIpml service;

    public DepartamentoController(DepartamentoServiceIpml service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<DepartamentoDTO>> findAll() {
        List<Departamento> departamentos = service.findAll();
        List<DepartamentoDTO> departamentosDTO = departamentos.stream().map(DepartamentoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(departamentosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        Departamento departamento = service.findById(id);
        return ResponseEntity.ok().body(new DepartamentoDTO(departamento));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody DepartamentoDTO departamentoDTO) {
        Departamento departamento = service.create(departamentoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(departamento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody DepartamentoDTO departamentoDTO, @PathVariable Long id) {
        Departamento departamento = new Departamento();
        departamento.setId(id);
        service.update(id, departamentoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}