package com.diogoamorim.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.diogoamorim.cursomc.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, String> {

	@Transactional(readOnly = true)
	List<Estado> findAllByOrderByNome();

}
