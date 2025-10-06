package com.delegrego.exemplo_jpa_2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.delegrego.exemplo_jpa_2.dto.DepartamentoDto;
import com.delegrego.exemplo_jpa_2.entity.DepartamentoEntity;
import com.delegrego.exemplo_jpa_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_jpa_2.repo.FuncionarioRepository;

import jakarta.validation.Valid;

/**
 * Serviço para gerenciar operações relacionadas a Departamentos. Inclui métodos
 * para criar, ler, atualizar e deletar departamentos, além de validações
 * específicas.
 */

// Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service
@Validated
public class DepartamentoService {

	// Autowired injeta automaticamente a interface de repositório que acessa o
	// banco de dados
	@Autowired
	private DepartamentoRepository departamentoRepo;

	@Autowired
	private FuncionarioRepository funcionarioRepo;

	/**
	 * Create: Cadastra um novo departamento no sistema.
	 * 
	 * @param d - O departamento a ser cadastrado.
	 */
	public void cadastrarDepartamento(@Valid DepartamentoDto d) {

		DepartamentoEntity departamentoEntity = new DepartamentoEntity();
		departamentoEntity.setNmDepartamento(d.getNmDepartamento());
		departamentoRepo.save(departamentoEntity);

	}

	/**
	 * Read: Lista todos os departamentos cadastrados no sistema.
	 * 
	 * @return Uma lista de departamentos.
	 */
	public List<DepartamentoDto> listarDepartamentos() {
		List<DepartamentoEntity> listaDepartamentoEntity = departamentoRepo.findAll();

		List<DepartamentoDto> listaDepartamentoDto = new ArrayList<>();

		for (DepartamentoEntity d : listaDepartamentoEntity) {
			DepartamentoDto departamentoDto = new DepartamentoDto();

			departamentoDto.setIdDepartamento(d.getIdDepartamento());
			departamentoDto.setNmDepartamento(d.getNmDepartamento());

			listaDepartamentoDto.add(departamentoDto);
		}

		return listaDepartamentoDto;
	}

	/**
	 * Update: Atualiza as informações de um departamento existente.
	 * 
	 * @param d - O departamento com as informações atualizadas.
	 * @throws RuntimeException se o departamento não existir.
	 */

	// TODO: Fazer com que venha um id como parâmetro
	public void atualizarDepartamento(DepartamentoDto d) {

		DepartamentoEntity departamento = departamentoRepo.findById(d.getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não existe"));

		departamento.setNmDepartamento(d.getNmDepartamento());
		departamentoRepo.save(departamento);

	}

	/**
	 * Delete: Deleta um departamento pelo seu ID.
	 * 
	 * @param id - O ID do departamento a ser deletado.
	 * @throws RuntimeException se o departamento não existir ou se houver
	 *                          funcionários associados.
	 */
	// TODO: Refatorar
	public void deletarDepartamento(int id) {

		departamentoRepo.findById(id).orElseThrow(() -> new RuntimeException("Departamento não existe"));

		if (funcionarioRepo.existsByDepartamentoIdDepartamento(id)) {
			throw new RuntimeException("Não pode excluir departamentos com funcionários");
		}

		departamentoRepo.deleteById(id);
	}
}
