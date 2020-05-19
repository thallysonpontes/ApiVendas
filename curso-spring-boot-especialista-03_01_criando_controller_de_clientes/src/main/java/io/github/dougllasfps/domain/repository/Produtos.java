package io.github.dougllasfps.domain.repository;

import io.github.dougllasfps.domain.entity.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
	
}
