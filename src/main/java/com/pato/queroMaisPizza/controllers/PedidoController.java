package com.pato.queroMaisPizza.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pato.queroMaisPizza.entities.Pedido;
import com.pato.queroMaisPizza.enums.StatusPedido;
import com.pato.queroMaisPizza.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // POST /pedidos - cria pedido
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        Pedido novo = pedidoService.criar(pedido);
        URI uri = URI.create("/pedidos/" + novo.getId());
        return ResponseEntity.created(uri).body(novo);
    }

    // GET /pedidos - lista todos
    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    // GET /pedidos/1
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    // PATCH /pedidos/1/status - muda status
    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String status = body.get("status");
        if (status == null)
            status = body.get("statusPedido"); // aceita os dois
        StatusPedido novoStatus = StatusPedido.valueOf(status.toUpperCase());
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, novoStatus));
    }

    // DELETE /pedidos/1 - cancela (não apaga do banco)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        pedidoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}