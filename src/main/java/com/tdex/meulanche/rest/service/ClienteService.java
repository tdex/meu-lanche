package com.tdex.meulanche.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tdex.meulanche.domain.entity.Cliente;
import com.tdex.meulanche.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente adicionarCliente(Cliente cli) {
		return repository.save(cli);
	}

	public void desativarCliente(String cpf) {
		repository.findById(cpf).map(cli -> {
			cli.setAtivo(Boolean.FALSE);
			return repository.save(cli);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
	}
}
