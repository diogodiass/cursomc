package com.diogoamorim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogoamorim.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
