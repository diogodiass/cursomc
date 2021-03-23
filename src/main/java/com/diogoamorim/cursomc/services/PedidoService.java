package com.diogoamorim.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diogoamorim.cursomc.domain.ItemPedido;
import com.diogoamorim.cursomc.domain.PagamentoComBoleto;
import com.diogoamorim.cursomc.domain.Pedido;
import com.diogoamorim.cursomc.repositories.ItemPedidoRepository;
import com.diogoamorim.cursomc.repositories.PagamentoRepository;
import com.diogoamorim.cursomc.repositories.PedidoRepository;
import com.diogoamorim.cursomc.repositories.ProdutoRepository;
import com.diogoamorim.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> ped = pedidoRepository.findById(id);
		return ped.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID" + id + ",Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
	

}
