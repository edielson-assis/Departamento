package com.grupo1projeto1.cadastroDeFuncionario.services.ipml;

import java.util.List;
import java.util.Optional;

import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.grupo1projeto1.cadastroDeFuncionario.dto.FuncionarioDTO;
import com.grupo1projeto1.cadastroDeFuncionario.dto.FuncionarioUpdateDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Funcionario;
import com.grupo1projeto1.cadastroDeFuncionario.repositories.FuncionarioRepository;
import com.grupo1projeto1.cadastroDeFuncionario.services.FuncionarioService;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.DataBaseException;
import com.grupo1projeto1.cadastroDeFuncionario.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioServiceIpml implements FuncionarioService {

    @Autowired
    private CargoServiceIpml cargoService;

    @Autowired
    private DepartamentoServiceIpml departamentoService;

    private final FuncionarioRepository repository;

    public FuncionarioServiceIpml(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    @Override
    public Funcionario findById(Long id) {
        Optional<Funcionario> funcionario = repository.findById(id);
        return funcionario.orElseThrow(() -> new ObjectNotFoundException("Funcionario nao encontrado. Id invalido: " + id));
    }

    @Override
    public Funcionario create(FuncionarioDTO funcionarioDTO) {
        try {
            Funcionario funcionario = fromDTO(funcionarioDTO);
            return repository.save(funcionario);
        }
        catch (RuntimeException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Funcionario update(Long id, FuncionarioUpdateDTO funcionarioUpdateDTO) {
        Funcionario funcionario = findById(id);
        try {
            updateData(funcionario, funcionarioUpdateDTO);
            cargoService.findById(funcionarioUpdateDTO.getCargo().getId());
            departamentoService.findById(funcionarioUpdateDTO.getDepartamento().getId());
            return repository.save(funcionario);
        }
        catch (RuntimeException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private void updateData(Funcionario funcionario, FuncionarioUpdateDTO funcionarioUpdateDTO) {
        funcionario.setNome(funcionarioUpdateDTO.getNome());
        funcionario.setTelefone(funcionarioUpdateDTO.getTelefone());
        funcionario.setTelefoneContato(funcionarioUpdateDTO.getTelefoneContato());
        funcionario.setSalario(funcionarioUpdateDTO.getSalario());
        funcionario.setCargo(new Cargo(funcionarioUpdateDTO.getCargo().getId(), null));
        funcionario.setDepartamento(new Departamento(funcionarioUpdateDTO.getDepartamento().getId(), null));
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

    private Funcionario fromDTO(FuncionarioDTO funcionarioDTO) {
        return new Funcionario(
                funcionarioDTO.getId(), 
                funcionarioDTO.getMatricula(), 
                funcionarioDTO.getNome(), 
                funcionarioDTO.getTelefone(), 
                funcionarioDTO.getTelefoneContato(), 
                funcionarioDTO.getSalario(), 
                new Cargo(funcionarioDTO.getCargo().getId(), null),
                new Departamento(funcionarioDTO.getDepartamento().getId(), null));
    }
}