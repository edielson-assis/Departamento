package com.grupo1projeto1.cadastroDeFuncionario.services;

import java.util.List;

import com.grupo1projeto1.cadastroDeFuncionario.dto.CargoDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Cargo;

public interface CargoService {
    
  /**
   * Retorna todas os Cargos que estão no banco de dados.
   * @return - Uma lista com todos Cargos que estão salvas no DB.
   */
    List<Cargo> findAll();

  /**
   * Retorna um Cargo que está no banco de dados de acordo com seu Id.
   * @param id - id do Cargo que será exibida.
   * @return - Cargo de acordo com o Id fornecido.
   */
    Cargo findById(Long id);

  /**
   * Cria um Cargo e salva no banco de dados.
   * @param cargoDTO - objeto referente aos dados para criação do Cargo no banco de dados.
   * @return - Cargo recém-criado.
   */
    Cargo create(CargoDTO cargoDTO);

  /**
   * Atualiza o Cargo.
   * @param id - id do Cargo que será atualizado.
   * @param cargoDTO - objeto referente aos dados necessários para atualização do Cargo no banco de dados.
   * @return - Cargo recém-atualizado.
   */
    Cargo update(Long id, CargoDTO cargoDTO);

  /**
   * Deleta um Cargo específico.
   * @param id - id do Cargo que será removido.
   */
    void delete(Long id);
}