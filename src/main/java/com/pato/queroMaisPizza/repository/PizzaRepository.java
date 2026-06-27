package com.pato.queroMaisPizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pato.queroMaisPizza.entities.Pizza;
@RepositoryRestResource(path = "pizzas")
public interface PizzaRepository extends JpaRepository<Pizza, Long> {}