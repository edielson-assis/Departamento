package com.grupo1projeto1.cadastroDeFuncionario.services;

import java.util.List;

import com.grupo1projeto1.cadastroDeFuncionario.dto.FuncionarioDTO;
import com.grupo1projeto1.cadastroDeFuncionario.dto.FuncionarioUpdateDTO;
import com.grupo1projeto1.cadastroDeFuncionario.entities.Funcionario;

public interface FuncionarioService {
    
  /**
   * Retorna todas os Funcionarios que estão no banco de dados.
   * @return - Uma lista com todos Funcionarios que estão salvas no DB.
   */
    List<Funcionario> findAll();

  /**
   * Retorna um Funcionario que está no banco de dados de acordo com seu Id.
   * @param id - id do Funcionario que será exibida.
   * @return - Funcionario de acordo com o Id fornecido.
   */
    Funcionario findById(Long id);

  /**
   * Cria um Funcionario e salva no banco de dados.
   * @param funcionarioDTO - objeto referente aos dados para criação do Funcionario no banco de dados.
   * @return - Funcionario recém-criado.
   */
    Funcionario create(FuncionarioDTO funcionarioDTO);

  /**
   * Atualiza o Funcionario.
   * @param id - id do Funcionario que será atualizado.
   * @param funcionarioUpdateDTO - objeto referente aos dados necessários para atualização do Funcionario no banco de dados.
   * @return - Funcionario recém-atualizado.
   */
    Funcionario update(Long id, FuncionarioUpdateDTO funcionarioUpdateDTO);

  /**
   * Deleta um Funcionario específico.
   * @param id - id do Funcionario que será removido.
   */
    void delete(Long id);
}