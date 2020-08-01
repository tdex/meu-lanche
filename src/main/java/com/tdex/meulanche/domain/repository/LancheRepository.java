package com.tdex.meulanche.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdex.meulanche.domain.entity.Lanche;

public interface LancheRepository extends JpaRepository<Lanche, Integer> {

	List<Lanche> findByIsLancheCustom(Boolean isLancheCustom);

}
