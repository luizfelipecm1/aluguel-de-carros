package com.example.alugueldeveiculos.controller;

import com.example.alugueldeveiculos.model.PedidoEntity;
import com.example.alugueldeveiculos.repository.PedidoRepository;
import com.example.alugueldeveiculos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<PedidoEntity> createPedido(@RequestBody PedidoEntity pedido) {
        // Aqui você deve ter um método para garantir que o automovel_id não seja nulo
        if (pedido.getAutomovel() == null) {
            return ResponseEntity.badRequest().body(null); // Retorna um erro se o ID do carro não for fornecido
        }

        PedidoEntity savedPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(savedPedido);
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
