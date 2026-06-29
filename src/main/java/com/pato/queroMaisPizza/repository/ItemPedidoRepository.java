package com.pato.queroMaisPizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pato.queroMaisPizza.entities.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {}