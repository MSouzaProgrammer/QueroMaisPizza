package com.pato.queroMaisPizza.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pizza")
@Getter
@Setter
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sabor;
    private Double precoBase;

    public Pizza(){}

    public Pizza(Long id, String sabor, Double precoBase) {
        this.id = id;
        this.sabor = sabor;
        this.precoBase = precoBase;
    }
    
}