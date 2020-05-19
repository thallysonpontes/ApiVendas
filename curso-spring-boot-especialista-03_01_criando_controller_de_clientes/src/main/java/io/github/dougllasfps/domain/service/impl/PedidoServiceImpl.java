package io.github.dougllasfps.domain.service.impl;

import org.springframework.stereotype.Service;

import io.github.dougllasfps.domain.repository.Pedidos;
import io.github.dougllasfps.domain.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private Pedidos pedidos;

	public PedidoServiceImpl(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	
}
