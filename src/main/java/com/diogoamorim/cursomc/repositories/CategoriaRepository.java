package com.diogoamorim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogoamorim.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
