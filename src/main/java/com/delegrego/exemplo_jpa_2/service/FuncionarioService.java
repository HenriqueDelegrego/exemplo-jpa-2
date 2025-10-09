package com.delegrego.exemplo_jpa_2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.delegrego.exemplo_jpa_2.dto.FuncionarioDto;
import com.delegrego.exemplo_jpa_2.entity.DepartamentoEntity;
import com.delegrego.exemplo_jpa_2.entity.FuncionarioEntity;
import com.delegrego.exemplo_jpa_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_jpa_2.repo.FuncionarioRepository;

import jakarta.validation.Valid;

/**
 * Serviço para gerenciar operações relacionadas a funcionários.
 */

// Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service

// Habilita a validação de métodos nesta classe
@Validated
public class FuncionarioService {

	// Autowired injeta automaticamente a interface de repositório que acessa o
	// banco de dados
	@Autowired
	private FuncionarioRepository funcionarioRepo;

	@Autowired
	private DepartamentoRepository departamentoRepo;

	/**
	 * Create: Cadastra um novo funcionário no sistema.
	 * 
	 * @param funcionarioDto - O funcionário a ser cadastrado.
	 * @throws RuntimeException se já existir um funcionário com o mesmo email ou se
	 *                          o departamento não existir.
	 */
	public void cadastrarFuncionario(@Valid FuncionarioDto funcionarioDto) {

		if (funcionarioRepo.findByEmail(funcionarioDto.getEmail()).isPresent()) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		// TODO: Para a solução inicial, fazer isso ou popular somente o id de um dptoEntity vazio?
		DepartamentoEntity departamentoEntity = departamentoRepo.findById(funcionarioDto.getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não existe"));

		FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
		funcionarioEntity.setNome(funcionarioDto.getNome());
		funcionarioEntity.setEmail(funcionarioDto.getEmail());
		funcionarioEntity.setSenha(funcionarioDto.getSenha());
		funcionarioEntity.setSalario(funcionarioDto.getSalario());
		funcionarioEntity.setDepartamento(departamentoEntity);

		funcionarioRepo.save(funcionarioEntity);

	}

	/**
	 * Read: Lista todos os funcionários cadastrados no sistema.
	 * 
	 * @return Uma lista de funcionários.
	 */
	public List<FuncionarioDto> listarFuncionarios() {

		List<FuncionarioEntity> listaFuncionarioEntity = funcionarioRepo.findAll();

		List<FuncionarioDto> listaFuncionarioDto = new ArrayList<>();

		for (FuncionarioEntity f : listaFuncionarioEntity) {
			FuncionarioDto funcionarioDto = new FuncionarioDto();
			funcionarioDto.setIdFuncionario(f.getIdFuncionario());
			funcionarioDto.setNome(f.getNome());
			funcionarioDto.setEmail(f.getNome());
			funcionarioDto.setSenha(f.getNome());
			funcionarioDto.setSalario(f.getSalario());
			funcionarioDto.setIdDepartamento(f.getDepartamento().getIdDepartamento());

			listaFuncionarioDto.add(funcionarioDto);

		}

		return listaFuncionarioDto;
	}

	/**
	 * Update: Atualiza as informações de um funcionário existente.
	 * 
	 * @param funcionarioDto - O funcionário com as informações atualizadas.
	 * @throws RuntimeException se o funcionário não existir, se já existir outro
	 *                          funcionário com o mesmo email, ou se o departamento
	 *                          não existir.
	 */
	public void atualizarFuncionario(FuncionarioDto funcionarioDto) {

		FuncionarioEntity funcionarioEntity = funcionarioRepo.findById(funcionarioDto.getIdFuncionario())
				.orElseThrow(() -> new RuntimeException("Funcionário não existe"));

		if (funcionarioRepo.existsByEmailAndIdFuncionarioNot(funcionarioDto.getEmail(),
				funcionarioDto.getIdFuncionario())) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		DepartamentoEntity departamentoEntity = departamentoRepo.findById(funcionarioDto.getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não existe"));

		funcionarioEntity.setNome(funcionarioDto.getNome());
		funcionarioEntity.setEmail(funcionarioDto.getEmail());
		funcionarioEntity.setSenha(funcionarioDto.getSenha());
		funcionarioEntity.setSalario(funcionarioDto.getSalario());
		funcionarioEntity.setDepartamento(departamentoEntity);

		funcionarioRepo.save(funcionarioEntity);

	}

	/**
	 * Delete: Deleta um funcionário pelo seu ID.
	 * 
	 * @param id - O ID do funcionário a ser deletado.
	 * @throws RuntimeException se o funcionário não existir.
	 */
	public void deletarFuncionario(int id) {
		funcionarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não existe"));

		funcionarioRepo.deleteById(id);
	}

}
