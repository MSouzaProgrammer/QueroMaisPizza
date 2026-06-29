package com.pato.queroMaisPizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pato.queroMaisPizza.entities.Address;

public interface EnderecoRepository extends JpaRepository<Address, Long> {}
