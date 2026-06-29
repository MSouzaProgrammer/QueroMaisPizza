package com.pato.queroMaisPizza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pato.queroMaisPizza.entities.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findBySaborIgnoreCase(String sabor);
}