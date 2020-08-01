package com.tdex.meulanche.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lanche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Descrição do lanche é obrigatória.")
	private String descricao;

	@ManyToMany
	@NotNull(message = "O lanche precisa ter ingredientes")
	private List<ItemIngrediente> ingredientes;

	@JsonIgnore
	private Boolean isLancheCustom;
}
