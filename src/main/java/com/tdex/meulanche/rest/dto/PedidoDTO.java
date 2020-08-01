package com.tdex.meulanche.rest.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoDTO {

	private String cpf;
	private List<Integer> idLanches;
}
