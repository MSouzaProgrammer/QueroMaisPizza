package com.pato.queroMaisPizza.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pato.queroMaisPizza.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant momento;
    private StatusPedido statusPedido;
    private Double total;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemPedido> itens = new ArrayList<>(); // inicializa!

    public Pedido() {
    }

    public Pedido(Long id, Instant momento, StatusPedido statusPedido, Double total, Address address,
            List<ItemPedido> itens) {
        this.id = id;
        this.momento = momento;
        this.statusPedido = statusPedido;
        this.total = total;
        this.address = address;
        this.itens = itens;
    }
}