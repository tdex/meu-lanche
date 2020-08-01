package com.tdex.meulanche.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.tdex.meulanche.domain.enums.StatusPedidoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@NotNull(message = "Cliente é obrigatório")
	private Cliente cliente;

	@ManyToMany
	@NotNull(message = "Pedido deve conter ao menos um lanche.")
	private List<Lanche> lanches;

	private Double preco;

	@Enumerated(EnumType.STRING)
	private StatusPedidoEnum status;

}
