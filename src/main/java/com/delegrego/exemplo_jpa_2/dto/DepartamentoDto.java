package com.delegrego.exemplo_jpa_2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) para representar um departamento. Inclui
 * validações para garantir que os dados estejam corretos.
 */
public class DepartamentoDto {

	private int idDepartamento;

	@NotBlank(message = "Nome do departamento não pode ser vazio")
	@Size(max = 50, message = "Departamento não pode ultrapassar 50 caracteres")
	private String nomeDepartamento;

	public DepartamentoDto() {

	}

	public DepartamentoDto(int idDepartamento, String nomeDepartamento) {
		this.idDepartamento = idDepartamento;
		this.nomeDepartamento = nomeDepartamento;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	@Override
	public String toString() {
		return "DepartamentoDto [idDepartamento=" + idDepartamento + ", nomeDepartamento=" + nomeDepartamento + "]";
	}

}
