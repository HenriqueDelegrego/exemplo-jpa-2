package com.delegrego.exemplo_jpa_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.delegrego.exemplo_jpa_2.dto.DepartamentoDto;
import com.delegrego.exemplo_jpa_2.dto.FuncionarioDto;
import com.delegrego.exemplo_jpa_2.service.DepartamentoService;
import com.delegrego.exemplo_jpa_2.service.FuncionarioService;

/**
 * Controlador para executar operações iniciais na aplicação. Implementa
 * CommandLineRunner para executar código após o início da aplicação.
 */

// Indica que esta classe é um componente do Spring
@Component
public class Controller implements CommandLineRunner {

	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public void run(String... args) {

		// CRUD para Departamento

		// Create
		DepartamentoDto departamentoNovo = new DepartamentoDto();
		departamentoNovo.setNmDepartamento("Financeiro");
		departamentoService.cadastrarDepartamento(departamentoNovo);

		DepartamentoDto departamentoNovo2 = new DepartamentoDto();
		departamentoNovo2.setNmDepartamento("Desenvolvimento");
		departamentoService.cadastrarDepartamento(departamentoNovo2);

		DepartamentoDto departamentoNovo3 = new DepartamentoDto();
		departamentoNovo3.setNmDepartamento("RH");
		departamentoService.cadastrarDepartamento(departamentoNovo3);

		// Read
		System.out.println(departamentoService.listarDepartamentos());

		// Update
		DepartamentoDto departamentoAtualizado = new DepartamentoDto();
		departamentoAtualizado.setNmDepartamento("Recursos Humanos");
		departamentoService.atualizarDepartamento(3, departamentoAtualizado);

		// Delete
		departamentoService.deletarDepartamento(3);

		// CRUD para Funcionario

		// Create

		FuncionarioDto funcionarioNovo = new FuncionarioDto();
		funcionarioNovo.setNome("João");
		funcionarioNovo.setEmail("joao@email.com");
		funcionarioNovo.setSenha("senha_joao");
		funcionarioNovo.setSalario(5000);
		funcionarioNovo.setIdDepartamento(1);
		funcionarioService.cadastrarFuncionario(funcionarioNovo);

		// Read
		System.out.println(funcionarioService.listarFuncionarios());

		// Update

		FuncionarioDto funcionarioAtualizado = new FuncionarioDto();
		funcionarioAtualizado.setNome("João Da Silva");
		funcionarioAtualizado.setEmail("novojoao@email.com");
		funcionarioAtualizado.setSenha("senha_joao_novo");
		funcionarioAtualizado.setSalario(5500);
		funcionarioAtualizado.setIdDepartamento(2);
		funcionarioService.atualizarFuncionario(1, funcionarioAtualizado);

		// Delete
		funcionarioService.deletarFuncionario(1);

	}

}
