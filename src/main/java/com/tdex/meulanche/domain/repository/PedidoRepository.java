package com.tdex.meulanche.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdex.meulanche.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
