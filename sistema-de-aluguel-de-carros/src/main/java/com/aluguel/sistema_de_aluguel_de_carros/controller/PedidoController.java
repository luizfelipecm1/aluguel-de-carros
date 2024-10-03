package com.aluguel.sistema_de_aluguel_de_carros.controller;

import com.aluguel.sistema_de_aluguel_de_carros.model.PedidoEntity;
import com.aluguel.sistema_de_aluguel_de_carros.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoEntity> criarPedido(@RequestBody PedidoEntity pedido){
        PedidoEntity novoPedido = pedidoService.criarPedido((pedido));
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity>buscarPedidoPorId(@PathVariable int idPedido){
        Optional<PedidoEntity> pedido = pedidoService.buscarPedidoPoriD(idPedido);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PedidoEntity> listarTodosOsPedidos(){
        return pedidoService.listarTodosPedidos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> atualizarPedido(@PathVariable int idPedido, @RequestBody PedidoEntity pedido){
        pedido.setIdPedido(idPedido);
        PedidoEntity pedidoAtualizado = pedidoService.atualizarPedido(pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable int idPedido){
        pedidoService.cancelarPedido(idPedido);
        return ResponseEntity.noContent().build();
    }
}
