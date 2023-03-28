package com.grupo1projeto1.cadastroDeFuncionario.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grupo1projeto1.cadastroDeFuncionario.dto.CargoDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;
import com.grupo1projeto1.cadastroDeFuncionario.services.ipml.CargoServiceIpml;

import jakarta.validation.Valid;

@RequestMapping(value = "/cargo")
@RestController
public class CargoController {
    
    private final CargoServiceIpml service;

    public CargoController(CargoServiceIpml service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<CargoDTO>> findAll() {
        List<Cargo> cargos = service.findAll();
        List<CargoDTO> cargosDTO = cargos.stream().map(CargoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(cargosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CargoDTO> findById(@PathVariable Long id) {
        Cargo cargo = service.findById(id);
        return ResponseEntity.ok().body(new CargoDTO(cargo));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CargoDTO cargoDTO) {
        Cargo cargo = service.create(cargoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(cargo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CargoDTO cargoDTO, @PathVariable Long id) {
        Cargo cargo = new Cargo();
        cargo.setId(id);
        service.update(id, cargoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}