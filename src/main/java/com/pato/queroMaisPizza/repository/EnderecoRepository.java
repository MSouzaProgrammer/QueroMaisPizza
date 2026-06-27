package com.pato.queroMaisPizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pato.queroMaisPizza.entities.Address;

@RepositoryRestResource(path = "edereco")
public interface EnderecoRepository extends JpaRepository<Address, Long> {}
