package com.tdex.meulanche.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

	@Id
	@CPF
	@NotNull(message = "CPF é obrigatório")
	private String cpf;

	@JsonIgnore
	private Boolean ativo;

	@NotNull(message = "Nome é obrigatório")
	private String nome;

	@Email
	@NotNull(message = "E-mail é obrigatório")
	private String email;

	@JsonIgnore
	private String senha;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pedido> pedidos;
}
