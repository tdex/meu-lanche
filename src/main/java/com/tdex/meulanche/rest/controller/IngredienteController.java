package com.tdex.meulanche.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdex.meulanche.domain.entity.Ingrediente;
import com.tdex.meulanche.rest.service.IngredienteService;

@RestController
@RequestMapping("ingrediente")
public class IngredienteController {

	@Autowired
	private IngredienteService service;

	@PostMapping
	public Ingrediente addIngrediente(@RequestBody @Valid Ingrediente ingrediente) {
		return service.addIngrediente(ingrediente);
	}

	@DeleteMapping("{id}")
	public void removerIngrediente(Integer id) {
		service.deleteIngrediente(id);
	}

	@PatchMapping
	public Ingrediente atualizarIngrediente(@RequestBody Ingrediente ing) {
		return service.atualizarIngrediente(ing);
	}

	@GetMapping
	public List<Ingrediente> listarIngredientes() {
		return service.listarIngredientes();
	}
}
