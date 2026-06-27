package com.pato.queroMaisPizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pato.queroMaisPizza.entities.Pedido;

@RepositoryRestResource(path = "pedido")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {}