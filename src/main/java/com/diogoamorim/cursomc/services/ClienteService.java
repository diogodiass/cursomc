package com.diogoamorim.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogoamorim.cursomc.domain.Categoria;
import com.diogoamorim.cursomc.domain.Cliente;
import com.diogoamorim.cursomc.exception.ObjectNotFoundException;
import com.diogoamorim.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cli = clienteRepository.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID" + id + ",Tipo: "
				+ Cliente.class.getName()));
	}

}
