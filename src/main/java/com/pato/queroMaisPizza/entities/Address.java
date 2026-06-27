package com.pato.queroMaisPizza.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String rua;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String referencia;
    
    public Address() {}
    public Address(String rua, Integer numero, String bairro, String complemento, String referencia) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.referencia = referencia;
    }
}