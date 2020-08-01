package com.tdex.meulanche.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdex.meulanche.domain.entity.Cliente;
import com.tdex.meulanche.rest.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping
	public Cliente addCliente(@RequestBody @Valid Cliente cli) {
		return service.adicionarCliente(cli);
	}

	@DeleteMapping("{cpf}")
	public void desativarCliente(String cpf) {
		service.desativarCliente(cpf);
	}

}
