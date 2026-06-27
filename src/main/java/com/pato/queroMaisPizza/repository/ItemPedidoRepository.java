package com.pato.queroMaisPizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pato.queroMaisPizza.entities.ItemPedido;

@RepositoryRestResource(path = "itemPedido")
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {}