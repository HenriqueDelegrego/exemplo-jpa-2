package com.delegrego.exemplo_jpa_2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.delegrego.exemplo_jpa_2.dto.DepartamentoDto;
import com.delegrego.exemplo_jpa_2.entity.DepartamentoEntity;
import com.delegrego.exemplo_jpa_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_jpa_2.repo.FuncionarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Serviço para gerenciar operações relacionadas a Departamentos. Inclui métodos
 * para criar, ler, atualizar e deletar departamentos, além de validações
 * específicas.
 */

// Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service

// Habilita a validação de métodos nesta classe
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
	 * @param departamentoDto - O departamento a ser cadastrado.
	 */
	public void cadastrarDepartamento(@Valid DepartamentoDto departamentoDto) {

		DepartamentoEntity departamentoEntity = new DepartamentoEntity();
		departamentoEntity.setNomeDepartamento(departamentoDto.getNomeDepartamento());

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
			departamentoDto.setNomeDepartamento(d.getNomeDepartamento());

			listaDepartamentoDto.add(departamentoDto);
		}

		return listaDepartamentoDto;
	}

	/**
	 * Read pesquisa parcial: Pesquisa departamentos pelo nome, permitindo buscas
	 * parciais e ignorando maiúsculas e minúsculas.
	 * 
	 * @param pesquisa - A string de pesquisa para o nome do departamento.
	 * @return Uma lista de departamentos que correspondem à pesquisa.
	 */
	public List<DepartamentoDto> pesquisarDepartamentos(@NotBlank @Size(max = 50) String pesquisa) {

		List<DepartamentoEntity> listaDepartamentoEntity = departamentoRepo
				.findByNomeDepartamentoContainingIgnoreCase(pesquisa);

		List<DepartamentoDto> listaDepartamentoDto = new ArrayList<>();

		for (DepartamentoEntity d : listaDepartamentoEntity) {
			DepartamentoDto departamentoDto = new DepartamentoDto();

			departamentoDto.setIdDepartamento(d.getIdDepartamento());
			departamentoDto.setNomeDepartamento(d.getNomeDepartamento());

			listaDepartamentoDto.add(departamentoDto);
		}

		return listaDepartamentoDto;
	}

	/**
	 * Read por ID: Obtém os detalhes de um departamento específico pelo seu ID.
	 * 
	 * @param id - O ID do departamento a ser obtido.
	 * @return Os detalhes do departamento.
	 * @throws RuntimeException se o departamento não existir.
	 */
	public DepartamentoDto obterDepartamentoPorId(int id) {

		DepartamentoEntity departamentoEntity = departamentoRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Departamento não existe"));

		DepartamentoDto departamentoDto = new DepartamentoDto();

		departamentoDto.setIdDepartamento(departamentoEntity.getIdDepartamento());
		departamentoDto.setNomeDepartamento(departamentoEntity.getNomeDepartamento());

		return departamentoDto;

	}

	/**
	 * Update: Atualiza os dados de um departamento existente.
	 * 
	 * @param id              - O ID do departamento a ser atualizado.
	 * @param departamentoDto - Os novos dados do departamento.
	 * @throws RuntimeException se o departamento não existir.
	 */
	public void atualizarDepartamento(int id, @Valid DepartamentoDto departamentoDto) {

		DepartamentoEntity departamentoEntity = departamentoRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Departamento não existe"));

		departamentoEntity.setNomeDepartamento(departamentoDto.getNomeDepartamento());

		departamentoRepo.save(departamentoEntity);

	}

	/**
	 * Delete: Deleta um departamento pelo seu ID.
	 * 
	 * @param id - O ID do departamento a ser deletado.
	 * @throws RuntimeException se o departamento não existir ou se houver
	 *                          funcionários associados.
	 */
	public void deletarDepartamento(int id) {

		if (!departamentoRepo.existsById(id)) {
			throw new RuntimeException("Departamento não existe");
		}

		if (funcionarioRepo.existsByDepartamentoIdDepartamento(id)) {
			throw new RuntimeException("Não pode excluir departamentos com funcionários");
		}

		departamentoRepo.deleteById(id);
	}
}
