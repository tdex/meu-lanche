package com.tdex.meulanche.rest.service;

import java.util.List;

import org.aspectj.apache.bcel.generic.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tdex.meulanche.domain.entity.Ingrediente;
import com.tdex.meulanche.domain.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository repository;

	public Ingrediente addIngrediente(Ingrediente ing) {
		return repository.save(ing);
	}

	public List<Ingrediente> listarIngredientes() {
		return repository.findAll();
	}

	public void deleteIngrediente(Integer id) {
		repository.findById(id).map(ingrediente -> {
			repository.deleteById(ingrediente.getId());
			return Type.VOID;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado."));
	}

	public Ingrediente atualizarIngrediente(Ingrediente ing) {
		if (ing.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informado o ID do ingrediente.");
		}

		return repository.findById(ing.getId()).map(ingrediente -> {
			return repository.save(ing);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado."));
	}
}
