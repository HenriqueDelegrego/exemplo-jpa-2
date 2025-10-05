package com.delegrego.exemplo_jpa_2.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class FuncionarioDto {

	private int idFuncionario;

	@NotBlank
	@Size(max = 100, message = "Nome inválido")
	private String nome;

	@Email(message = "Email inválido")
	private String email;

	@NotBlank
	@Size(max = 50, message = "Senha inválida")
	private String senha;

	@Digits(integer = 10, fraction = 2, message = "Salário inválido")
	@PositiveOrZero(message = "Salário não pode ser negativo")
	private double salario;

	private int idDepartamento;

	public FuncionarioDto() {

	}

	public FuncionarioDto(int idFuncionario, String nome, String email, String senha, double salario,
			int idDepartamento) {
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.salario = salario;
		this.idDepartamento = idDepartamento;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toString() {
		return "FuncionarioDto [idFuncionario=" + idFuncionario + ", nome=" + nome + ", email=" + email + ", senha="
				+ senha + ", salario=" + salario + ", idDepartamento=" + idDepartamento + "]";
	}

}
