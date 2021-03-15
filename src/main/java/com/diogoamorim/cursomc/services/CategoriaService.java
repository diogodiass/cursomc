package com.diogoamorim.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogoamorim.cursomc.domain.Categoria;
import com.diogoamorim.cursomc.exception.ObjectNotFoundException;
import com.diogoamorim.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> cat = categoriaRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID" + id + ",Tipo: "
				+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria upate(Categoria obj) {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}

}
