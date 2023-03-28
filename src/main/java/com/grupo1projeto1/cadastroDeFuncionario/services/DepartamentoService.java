package com.grupo1projeto1.cadastroDeFuncionario.services;

import java.util.List;

import com.grupo1projeto1.cadastroDeFuncionario.dto.DepartamentoDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Departamento;

public interface DepartamentoService {
 
  /**
   * Retorna todas os Departamentos que estão no banco de dados.
   * @return - Uma lista com todos Departamentos que estão salvas no DB.
   */
    List<Departamento> findAll();

  /**
   * Retorna um Departamento que está no banco de dados de acordo com seu Id.
   * @param id - id do Departamento que será exibida.
   * @return - Departamento de acordo com o Id fornecido.
   */
    Departamento findById(Long id);

  /**
   * Cria um Departamento e salva no banco de dados.
   * @param departamentoDTO - objeto referente aos dados para criação do Departamento no banco de dados.
   * @return - Departamento recém-criado.
   */
    Departamento create(DepartamentoDTO departamentoDTO);

  /**
   * Atualiza o Departamento.
   * @param id - id do Departamento que será atualizado.
   * @param departamentoDTO - objeto referente aos dados necessários para atualização do Departamento no banco de dados.
   * @return - Departamento recém-atualizado.
   */
    Departamento update(Long id, DepartamentoDTO departamentoDTO);

  /**
   * Deleta um Departamento específico.
   * @param id - id do Departamento que será removido.
   */
    void delete(Long id);
}