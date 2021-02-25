package com.diogoamorim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogoamorim.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto	, Integer> {

}
