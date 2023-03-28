package com.grupo1projeto1.cadastroDeFuncionario.services.ipml;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.grupo1projeto1.cadastroDeFuncionario.dto.CargoDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;
import com.grupo1projeto1.cadastroDeFuncionario.repositories.CargoRepository;
import com.grupo1projeto1.cadastroDeFuncionario.services.CargoService;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.BusinessException;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.DataBaseException;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.ObjectNotFoundException;

@Service
public class CargoServiceIpml implements CargoService {

    private final CargoRepository repository;

    public CargoServiceIpml(CargoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cargo> findAll() {
        return repository.findAll();
    }

    @Override
    public Cargo findById(Long id) {
        Optional<Cargo> cargo = repository.findById(id);
        return cargo.orElseThrow(() -> new ObjectNotFoundException("Cargo nao encontrado. Id invalido: " + id));
    }

    @Override
    public Cargo create(CargoDTO cargoDTO) {
        Cargo cargo = fromDTO(cargoDTO);
        alreadyExists(cargo);
        return repository.save(cargo);
    }

    @Override
    public Cargo update(Long id, CargoDTO cargoDTO) {
        Cargo cargo = findById(id);
        updateData(cargo, cargoDTO);
        alreadyExists(cargo);
        return repository.save(cargo);
    }

    private void updateData(Cargo cargo, CargoDTO cargoDTO) {
        cargo.setNome(cargoDTO.getNome());
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } 
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private void alreadyExists(Cargo cargo) {
        List<Cargo> existeCargo = repository.findByNome(cargo.getNome());
        if (!existeCargo.isEmpty()) {
            throw new BusinessException("Cargo ja existe");
        }
    }

    private Cargo fromDTO(CargoDTO cargoDTO) {
        return new Cargo(cargoDTO.getId(), cargoDTO.getNome());
    }
}