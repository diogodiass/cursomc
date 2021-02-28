package com.diogoamorim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogoamorim.cursomc.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
