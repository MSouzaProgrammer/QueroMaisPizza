package com.pato.queroMaisPizza.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pato.queroMaisPizza.entities.Pizza;
import com.pato.queroMaisPizza.services.PizzaService;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

     private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    // GET /pizzas - cardápio
    @GetMapping
    public ResponseEntity<List<Pizza>> listar() {
        return ResponseEntity.ok(pizzaService.listar());
    }

    // GET /pizzas/1
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pizzaService.buscarPorId(id));
    }

    // POST /pizzas - cadastrar nova
    @PostMapping
    public ResponseEntity<Pizza> criar(@RequestBody Pizza pizza) {
        Pizza nova = pizzaService.criar(pizza);
        URI uri = URI.create("/pizzas/" + nova.getId());
        return ResponseEntity.created(uri).body(nova);
    }

    // PUT /pizzas/1 - atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> atualizar(@PathVariable Long id, @RequestBody Pizza pizza) {
        Pizza atualizada = pizzaService.atualizar(id, pizza);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE /pizzas/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pizzaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}