package com.delegrego.exemplo_jpa_2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_jpa_2.entity.FuncionarioEntity;

/**
 * Repositório JPA para a entidade Funcionario. Extende JpaRepository para
 * fornecer operações CRUD e outras funcionalidades. O JpaRepository é
 * parametrizado com a entidade Funcionario e o tipo da chave primária
 * (Integer).
 */
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {

	// Derived queries

	boolean existsByEmail(String email);

	/**
	 * Verifica se existe um funcionário com o mesmo email, excluindo um ID
	 * específico. Útil para validação ao atualizar um funcionário.
	 * 
	 * @param email - Email a ser verificado.
	 * @param id    - ID do funcionário a ser excluído da verificação.
	 * @return true se existir outro funcionário com o mesmo email, false caso
	 *         contrário.
	 */
	boolean existsByEmailAndIdFuncionarioNot(String email, int id);

	/**
	 * Verifica se existe algum funcionário associado a um departamento específico.
	 * Útil para validação antes de excluir um departamento.
	 * 
	 * @param idDepartamento - ID do departamento a ser verificado.
	 * @return true se existir pelo menos um funcionário no departamento, false caso
	 *         contrário.
	 */
	boolean existsByDepartamentoIdDepartamento(int idDepartamento);
}