package com.tdex.meulanche.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.aspectj.apache.bcel.generic.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tdex.meulanche.domain.entity.Lanche;
import com.tdex.meulanche.domain.repository.ItemIngredienteRepository;
import com.tdex.meulanche.domain.repository.LancheRepository;

@Service
public class LancheService {

	@Autowired
	private LancheRepository repository;

	@Autowired
	private ItemIngredienteRepository itemIngredienteRepository;

	public List<Lanche> listarLanches() {
		return repository.findByIsLancheCustom(Boolean.FALSE);
	}

	@Transactional
	public Lanche addLanche(Lanche lanche) {
		if (lanche.getIsLancheCustom() == null) {
			lanche.setIsLancheCustom(false);
		}

		itemIngredienteRepository.saveAll(lanche.getIngredientes());

		return repository.save(lanche);
	}

	public void removerLanche(Integer id) {
		repository.findById(id).map(lanche -> {
			repository.deleteById(lanche.getId());
			return Type.VOID;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lanche não encontrado."));
	}

}
