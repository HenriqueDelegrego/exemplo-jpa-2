package com.delegrego.exemplo_jpa_2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) para representar um departamento.
 * Inclui validações para garantir que os dados estejam corretos.
 */
public class DepartamentoDto {

	private int idDepartamento;

	@NotBlank(message = "Departamento não pode ser vazio")
	@Size(max = 50, message = "Departamento não pode ultrapassar 50 caracteres")
	private String nmDepartamento;

	public DepartamentoDto() {

	}

	public DepartamentoDto(int idDepartamento, String nmDepartamento) {
		this.idDepartamento = idDepartamento;
		this.nmDepartamento = nmDepartamento;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNmDepartamento() {
		return nmDepartamento;
	}

	public void setNmDepartamento(String nmDepartamento) {
		this.nmDepartamento = nmDepartamento;
	}

	@Override
	public String toString() {
		return "DepartamentoDto [idDepartamento=" + idDepartamento + ", nmDepartamento=" + nmDepartamento + "]";
	}

}
