package com.delegrego.exemplo_jpa_2.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.delegrego.exemplo_jpa_2.dto.DepartamentoDto;
import com.delegrego.exemplo_jpa_2.dto.FuncionarioDto;
import com.delegrego.exemplo_jpa_2.service.DepartamentoService;
import com.delegrego.exemplo_jpa_2.service.FuncionarioService;
import com.delegrego.exemplo_jpa_2.utils.ConsoleUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CLIController implements CommandLineRunner {

	private final DepartamentoService departamentoService;

	private final FuncionarioService funcionarioService;

	@Override
	public void run(String... args) {

		Scanner input = new Scanner(System.in);
		int opcao;

		System.out.print("\n=== Exemplo JPA 2 ===\n");

		do {

			ConsoleUtils.exibirMenu();

			opcao = input.nextInt();

			try {
				switch (opcao) {
				case 1 -> cadastrarDepartamento();
				case 2 -> listarDepartamentos();
				case 3 -> atualizarDepartamento();
				case 4 -> deletarDepartamento();
				case 5 -> cadastrarFuncionario();
				case 6 -> listarFuncionarios();
				case 7 -> atualizarFuncionario();
				case 8 -> deletarFuncionario();
				case 0 -> System.out.println("Saindo da aplicação...");
				default -> {
					System.out.println("Opção inválida\n");
					ConsoleUtils.exibirMenu();
					opcao = input.nextInt();
				}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (opcao != 0);

	}

	private void cadastrarDepartamento() {

		Scanner input = new Scanner(System.in);

		System.out.println("Insira o nome do Departamento: ");
		String nomeDepartamento = input.nextLine();

		DepartamentoDto departamento = new DepartamentoDto();
		departamento.setNomeDepartamento(nomeDepartamento);
		departamentoService.cadastrarDepartamento(departamento);

	}

	private void listarDepartamentos() {

		List<DepartamentoDto> listaDepartamentos = departamentoService.listarDepartamentos();

		if (listaDepartamentos.isEmpty()) {
			System.out.println("Não há departamentos");
		} else {
			System.out.println("Lista de departamentos: ");
			for (DepartamentoDto d : listaDepartamentos) {
				System.out.println(d);
			}
		}

	}

	private void atualizarDepartamento() {

		Scanner input = new Scanner(System.in);

		System.out.println("Insira a id do Departamento: ");
		int id = input.nextInt();

		System.out.println("Insira o nome do Departamento: ");
		input.nextLine();
		String nomeDepartamento = input.nextLine();

		DepartamentoDto departamento = new DepartamentoDto();
		departamento.setNomeDepartamento(nomeDepartamento);

		departamentoService.atualizarDepartamento(id, departamento);

	}

	private void deletarDepartamento() {

		Scanner input = new Scanner(System.in);

		System.out.println("Insira a id do Departamento: ");
		int id = input.nextInt();

		departamentoService.deletarDepartamento(id);

	}

	private void cadastrarFuncionario() {

		Scanner input = new Scanner(System.in);

		System.out.print("Informe o nome: ");
		String nome = input.nextLine();

		System.out.print("Informe o email: ");
		String email = input.nextLine();

		System.out.print("Informe a senha: ");
		String senha = input.nextLine();

		System.out.print("Informe o salário: ");
		double salario = input.nextDouble();

		System.out.print("Informe o ID do departamento: ");
		int idDepartamento = input.nextInt();

		FuncionarioDto funcionario = new FuncionarioDto();
		funcionario.setNome(nome);
		funcionario.setEmail(email);
		funcionario.setSenha(senha);
		funcionario.setSalario(salario);
		funcionario.setIdDepartamento(idDepartamento);

		funcionarioService.cadastrarFuncionario(funcionario);

	}

	private void listarFuncionarios() {

		List<FuncionarioDto> listaFuncionarios = funcionarioService.listarFuncionarios();

		if (listaFuncionarios.isEmpty()) {
			System.out.println("Não há funcionários");
		} else {
			System.out.println("Lista de funcionarios: ");
			for (FuncionarioDto f : listaFuncionarios) {
				System.out.println(f);
			}
		}

	}

	private void atualizarFuncionario() {

		Scanner input = new Scanner(System.in);

		System.out.println("Insira a id do funcionário: ");
		int id = input.nextInt();
		input.nextLine();

		System.out.print("Informe o nome: ");
		String nome = input.nextLine();

		System.out.print("Informe o email: ");
		String email = input.nextLine();

		System.out.print("Informe a senha: ");
		String senha = input.nextLine();

		System.out.print("Informe o salário: ");
		double salario = input.nextDouble();

		System.out.print("Informe o ID do departamento: ");
		int idDepartamento = input.nextInt();

		FuncionarioDto funcionario = new FuncionarioDto();
		funcionario.setNome(nome);
		funcionario.setEmail(email);
		funcionario.setSenha(senha);
		funcionario.setSalario(salario);
		funcionario.setIdDepartamento(idDepartamento);

		funcionarioService.atualizarFuncionario(id, funcionario);

	}

	private void deletarFuncionario() {

		Scanner input = new Scanner(System.in);

		System.out.println("Insira a id do Funcionario: ");
		int id = input.nextInt();

		funcionarioService.deletarFuncionario(id);

	}

}
