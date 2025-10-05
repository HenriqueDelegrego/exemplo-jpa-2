package com.delegrego.exemplo_jpa_2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_jpa_2.dto.FuncionarioDto;
import com.delegrego.exemplo_jpa_2.entity.FuncionarioEntity;
import com.delegrego.exemplo_jpa_2.repo.DepartamentoRepository;
import com.delegrego.exemplo_jpa_2.repo.FuncionarioRepository;

/**
 * Serviço para gerenciar operações relacionadas a funcionários.
 */

// Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service
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
	 * @param f - O funcionário a ser cadastrado.
	 * @throws RuntimeException se já existir um funcionário com o mesmo email ou se
	 *                          o departamento não existir.
	 */
	public void cadastrarFuncionario(FuncionarioDto f) {

		if (funcionarioRepo.findByEmail(f.getEmail()).isPresent()) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		departamentoRepo.findById(f.getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não existe"));

		FuncionarioEntity funcionario = new FuncionarioEntity();
		funcionario.setNome(f.getNome());
		funcionario.setEmail(f.getEmail());
		funcionario.setSenha(f.getSenha());
		funcionario.setSalario(f.getSalario());

		funcionarioRepo.save(funcionario);
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
	 * @param f - O funcionário com as informações atualizadas.
	 * @throws RuntimeException se o funcionário não existir, se já existir outro
	 *                          funcionário com o mesmo email, ou se o departamento
	 *                          não existir.
	 */
	public void atualizarFuncionario(FuncionarioDto f) {

		if (funcionarioRepo.existsByEmailAndIdFuncionarioNot(f.getEmail(), f.getIdFuncionario())) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		departamentoRepo.findById(f.getIdDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento não existe"));

		Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepo.findById(f.getIdFuncionario());

		if (funcionarioOptional.isPresent()) {
			FuncionarioEntity funcionario = funcionarioOptional.get();

			funcionario.setNome(f.getNome());
			funcionario.setEmail(f.getEmail());
			funcionario.setSenha(f.getSenha());
			funcionario.setSalario(f.getSalario());

			funcionarioRepo.save(funcionario);

		}

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
