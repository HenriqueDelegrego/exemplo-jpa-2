package com.delegrego.exemplo_jpa_2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_jpa_2.entity.DepartamentoEntity;

/**
 * Repositório JPA para a entidade Departamento. Extende JpaRepository para
 * fornecer operações CRUD e outras funcionalidades. O JpaRepository é
 * parametrizado com a entidade Departamento e o tipo da chave primária
 * (Integer).
 */
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {

}
