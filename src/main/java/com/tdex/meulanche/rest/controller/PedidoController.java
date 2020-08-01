package com.tdex.meulanche.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdex.meulanche.domain.entity.Pedido;
import com.tdex.meulanche.rest.dto.PedidoDTO;
import com.tdex.meulanche.rest.service.PedidoService;

@RestController
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping
	public Pedido realizarPedido(@RequestBody PedidoDTO pedido) {
		return service.addPedido(pedido);
	}

	@GetMapping("{id}")
	public Pedido getPedido(@PathVariable Long id) {
		return service.obterPedido(id);
	}

	@PatchMapping("{id}")
	public Pedido confirmarPedido(@PathVariable Long id) {
		return service.confirmarPedido(id);
	}

}
