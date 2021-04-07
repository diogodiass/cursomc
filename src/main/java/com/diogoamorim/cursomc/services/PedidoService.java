package com.diogoamorim.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diogoamorim.cursomc.domain.Cliente;
import com.diogoamorim.cursomc.domain.ItemPedido;
import com.diogoamorim.cursomc.domain.PagamentoComBoleto;
import com.diogoamorim.cursomc.domain.Pedido;
import com.diogoamorim.cursomc.domain.enums.EstadoPagamento;
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
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {
		Optional<Pedido> ped = pedidoRepository.findById(id);
		return ped.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID" + id + ",Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for(ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		} 
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
		return pedido;
		
		
	}
	

}
