package com.tdex.meulanche.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tdex.meulanche.domain.entity.Cliente;
import com.tdex.meulanche.domain.entity.ItemIngrediente;
import com.tdex.meulanche.domain.entity.Lanche;
import com.tdex.meulanche.domain.entity.Pedido;
import com.tdex.meulanche.domain.enums.StatusPedidoEnum;
import com.tdex.meulanche.domain.repository.ClienteRepository;
import com.tdex.meulanche.domain.repository.LancheRepository;
import com.tdex.meulanche.domain.repository.PedidoRepository;
import com.tdex.meulanche.rest.dto.PedidoDTO;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private LancheRepository lancheRepository;

	@Transactional
	public Pedido addPedido(PedidoDTO pedido) {
		Cliente cliente = clienteRepository.findById(pedido.getCpf())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		List<Lanche> lanches = lancheRepository.findAllById(pedido.getIdLanches());

		if (lanches.isEmpty() || pedido.getIdLanches().size() != lanches.size()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foram encontrados os lanches solicitados.");
		}

		Double preco = calculaPrecoPedido(lanches);

		Pedido pedidoCompleto = Pedido.builder()
				.cliente(cliente)
				.lanches(lanches)
				.preco(preco)
				.status(StatusPedidoEnum.PENDENTE)
				.build();

		return repository.save(pedidoCompleto);
	}

	private Double calculaPrecoPedido(List<Lanche> lanches) {
		Double valorTotal = (double) 0;

		for (Lanche lanchesList : lanches) {
			for (ItemIngrediente ingrediente : lanchesList.getIngredientes()) {
				valorTotal += ( ingrediente.getIngrediente().getPreco() * ingrediente.getQtd() );
			}
		}

		return valorTotal;
	}

	public Pedido obterPedido(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
	}

	@Transactional
	public Pedido confirmarPedido(Long id) {
		return repository.findById(id).map(pedido -> {
			pedido.setStatus(StatusPedidoEnum.CONFIRMADO);
			return repository.save(pedido);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
	}
}
