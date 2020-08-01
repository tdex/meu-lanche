package com.tdex.meulanche.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdex.meulanche.domain.entity.Lanche;
import com.tdex.meulanche.rest.dto.LancheDTO;
import com.tdex.meulanche.rest.service.LancheService;

@RestController
@RequestMapping("lanche")
public class LancheController {

	@Autowired
	private LancheService service;

	@GetMapping
	public List<Lanche> listarLanches() {
		return service.listarLanches();
	}

	@PostMapping
	public Lanche customLanche(@RequestBody @Valid LancheDTO lanche) {
		Lanche lancheCustom = lanche.getLanche();
		lancheCustom.setIsLancheCustom(true);
		lancheCustom.getIngredientes().addAll(lanche.getAdicional());

		return service.addLanche(lancheCustom);
	}
}
