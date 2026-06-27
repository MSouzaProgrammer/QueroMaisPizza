package com.pato.queroMaisPizza.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private String tamanho;
    private String borda;
    private Double precoUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido() {}
    public ItemPedido(Long id, Integer quantidade, String tamanho, String borda, Double precoUnitario, Pizza pizza,
            Pedido pedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.tamanho = tamanho;
        this.borda = borda;
        this.precoUnitario = precoUnitario;
        this.pizza = pizza;
        this.pedido = pedido;
    }
}