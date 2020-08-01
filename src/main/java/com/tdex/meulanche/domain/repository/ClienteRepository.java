package com.tdex.meulanche.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdex.meulanche.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
