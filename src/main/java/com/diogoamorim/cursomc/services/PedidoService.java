package com.diogoamorim.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogoamorim.cursomc.domain.Pedido;
import com.diogoamorim.cursomc.repositories.PedidoRepository;
import com.diogoamorim.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> ped = pedidoRepository.findById(id);
		return ped.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID" + id + ",Tipo: " + Pedido.class.getName()));
	}

}
