package com.pato.queroMaisPizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pato.queroMaisPizza.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}