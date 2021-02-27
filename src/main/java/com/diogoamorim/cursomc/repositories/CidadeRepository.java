package com.diogoamorim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogoamorim.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
