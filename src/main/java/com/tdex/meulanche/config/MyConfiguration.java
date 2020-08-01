package com.tdex.meulanche.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdex.meulanche.domain.entity.Ingrediente;
import com.tdex.meulanche.domain.entity.ItemIngrediente;
import com.tdex.meulanche.domain.entity.Lanche;
import com.tdex.meulanche.domain.repository.IngredienteRepository;
import com.tdex.meulanche.domain.repository.ItemIngredienteRepository;
import com.tdex.meulanche.domain.repository.LancheRepository;

@RestController
public class MyConfiguration {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private LancheRepository lancheRepository;

	@Autowired
	private ItemIngredienteRepository itemIngredienteRepository;

	@GetMapping("start")
	public String adicionarIngredientesAndLanches() {

		List<Ingrediente> ingredientesDefault = new ArrayList<>();
		Ingrediente alface = Ingrediente.builder().descricao("Alface").preco(0.4).build();
		Ingrediente bacon = Ingrediente.builder().descricao("Bacon").preco(2).build();
		Ingrediente hamburguer = Ingrediente.builder().descricao("Hambúguer").preco(3).build();
		Ingrediente ovo = Ingrediente.builder().descricao("Ovo").preco(0.8).build();
		Ingrediente queijo = Ingrediente.builder().descricao("Queijo").preco(1.5).build();

		ingredientesDefault.add(alface);
		ingredientesDefault.add(bacon);
		ingredientesDefault.add(hamburguer);
		ingredientesDefault.add(ovo);
		ingredientesDefault.add(queijo);

		ingredienteRepository.saveAll(ingredientesDefault);

		List<Lanche> lanchesDefault = new ArrayList<>();
		ItemIngrediente baconItem = ItemIngrediente.builder().ingrediente(bacon).qtd(1).build();
		ItemIngrediente hamburguerItem = ItemIngrediente.builder().ingrediente(hamburguer).qtd(1).build();
		ItemIngrediente queijoItem = ItemIngrediente.builder().ingrediente(queijo).qtd(1).build();
		ItemIngrediente ovoItem = ItemIngrediente.builder().ingrediente(ovo).qtd(1).build();

		List<ItemIngrediente> itens = Arrays.asList(baconItem, hamburguerItem, queijoItem, ovoItem);
		itemIngredienteRepository.saveAll(itens);

		List<ItemIngrediente> xBaconIngredientes = Arrays.asList(baconItem, hamburguerItem, queijoItem);
		lanchesDefault.add(Lanche.builder().descricao("X-Bacon").ingredientes(xBaconIngredientes).isLancheCustom(false).build());

		List<ItemIngrediente> xBurguerIngredientes = Arrays.asList(hamburguerItem, queijoItem);
		lanchesDefault.add(Lanche.builder().descricao("X-Burguer").ingredientes(xBurguerIngredientes).isLancheCustom(false).build());

		List<ItemIngrediente> xEggIngredientes = Arrays.asList(ovoItem, hamburguerItem, queijoItem);
		lanchesDefault.add(Lanche.builder().descricao("X-Egg").ingredientes(xEggIngredientes).isLancheCustom(false).build());

		List<ItemIngrediente> xEggBaconIngredientes = Arrays.asList(ovoItem, baconItem, hamburguerItem, queijoItem);
		lanchesDefault.add(Lanche.builder().descricao("X-Egg Bacon").ingredientes(xEggBaconIngredientes).isLancheCustom(false).build());

		lancheRepository.saveAll(lanchesDefault);

		return "Dados de cardápio inicial preenchido!";
	}
}
