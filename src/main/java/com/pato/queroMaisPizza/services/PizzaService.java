package com.pato.queroMaisPizza.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pato.queroMaisPizza.entities.Pizza;
import com.pato.queroMaisPizza.repository.PizzaRepository;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {

     private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> listar() {
        return pizzaRepository.findAll();
    }

    public Pizza buscarPorId(Long id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza id " + id + " não encontrada"));
    }

    @Transactional
    public Pizza criar(Pizza pizza) {
        // validação simples pra não duplicar nome no cardápio
        pizzaRepository.findBySaborIgnoreCase(pizza.getSabor())
                .ifPresent(p -> { throw new RuntimeException("Pizza '" + pizza.getSabor() + "' já existe"); });
        
        pizza.setId(null); // garante insert
        return pizzaRepository.save(pizza);
    }

    @Transactional
    public Pizza atualizar(Long id, Pizza dados) {
        Pizza pizza = buscarPorId(id);
        pizza.setSabor(dados.getSabor());
        pizza.setDescricao(dados.getDescricao());
        pizza.setPrecoBase(dados.getPrecoBase());
        pizza.setImagemUrl(dados.getImagemUrl());
        return pizzaRepository.save(pizza);
    }

    @Transactional
    public void remover(Long id) {
        Pizza pizza = buscarPorId(id);
        pizzaRepository.delete(pizza);
    }
}