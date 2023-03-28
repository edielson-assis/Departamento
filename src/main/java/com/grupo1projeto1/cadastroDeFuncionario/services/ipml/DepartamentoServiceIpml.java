package com.grupo1projeto1.cadastroDeFuncionario.services.ipml;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.grupo1projeto1.cadastroDeFuncionario.dto.DepartamentoDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;
import com.grupo1projeto1.cadastroDeFuncionario.repositories.DepartamentoRepository;
import com.grupo1projeto1.cadastroDeFuncionario.services.DepartamentoService;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.BusinessException;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.DataBaseException;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.ObjectNotFoundException;

@Service
public class DepartamentoServiceIpml implements DepartamentoService {
 
    private final DepartamentoRepository repository;

    public DepartamentoServiceIpml(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Departamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Departamento findById(Long id) {
        Optional<Departamento> departamento = repository.findById(id);
        return departamento.orElseThrow(() -> new ObjectNotFoundException("Departamento nao encontrado. Id invalido: " + id));
    }

    @Override
    public Departamento create(DepartamentoDTO departamentoDTO) {
        Departamento departamento = fromDTO(departamentoDTO);
        alreadyExists(departamento);
        return repository.save(departamento);
    }

    @Override
    public Departamento update(Long id, DepartamentoDTO departamentoDTO) {
        Departamento departamento = findById(id);
        updateData(departamento, departamentoDTO);
        alreadyExists(departamento);
        return repository.save(departamento);
    }

    private void updateData(Departamento departamento, DepartamentoDTO departamentoDTO) {
        departamento.setNome(departamentoDTO.getNome());
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

    private void alreadyExists(Departamento departamento) {
        List<Departamento> existeDepartamento = repository.findByNome(departamento.getNome());
        if (!existeDepartamento.isEmpty()) {
            throw new BusinessException("Departamento ja existe");
        }
    }

    private Departamento fromDTO(DepartamentoDTO departamentoDTO) {
        return new Departamento(departamentoDTO.getId(), departamentoDTO.getNome());
    }
}