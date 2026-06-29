package com.pato.queroMaisPizza.services;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pato.queroMaisPizza.entities.ItemPedido;
import com.pato.queroMaisPizza.entities.Pedido;
import com.pato.queroMaisPizza.entities.Pizza;
import com.pato.queroMaisPizza.enums.StatusPedido;
import com.pato.queroMaisPizza.repository.PedidoRepository;
import com.pato.queroMaisPizza.repository.PizzaRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PizzaRepository pizzaRepository;

    public PedidoService(PedidoRepository pedidoRepository, PizzaRepository pizzaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Transactional
    public Pedido criar(Pedido pedido) {
        pedido.setMomento(Instant.now());
        pedido.setStatusPedido(StatusPedido.RECEBIDO);

        double total = 0.0;

        for (ItemPedido item : pedido.getItens()) {
            // garante que a pizza existe e pega o preço oficial
            Pizza pizza = pizzaRepository.findById(item.getPizza().getId())
                    .orElseThrow(() -> new RuntimeException("Pizza id " + item.getPizza().getId() + " não encontrada"));

            item.setPizza(pizza);
            item.setPrecoUnitario(pizza.getPrecoBase());
            item.setPedido(pedido); // liga o lado ManyToOne

            total += item.getQuantidade() * item.getPrecoUnitario();
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido); // cascade salva itens e address
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido id " + id + " não encontrado"));
    }

    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatusPedido(novoStatus);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void cancelar(Long id) {
        Pedido pedido = buscarPorId(id);
        if (pedido.getStatusPedido() == StatusPedido.SAIU_ENTREGA || 
            pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Não pode cancelar pedido em rota");
        }
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }
    
}