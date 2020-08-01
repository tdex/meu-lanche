package com.tdex.meulanche.rest.dto;

import java.util.List;

import com.tdex.meulanche.domain.entity.ItemIngrediente;
import com.tdex.meulanche.domain.entity.Lanche;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LancheDTO {

	private Lanche lanche;

	private List<ItemIngrediente> adicional;
}
