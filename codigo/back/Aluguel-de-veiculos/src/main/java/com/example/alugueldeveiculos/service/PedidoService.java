package com.example.alugueldeveiculos.service;

import com.example.alugueldeveiculos.model.PedidoEntity;
import com.example.alugueldeveiculos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoEntity criarPedido(PedidoEntity pedido){
        return pedidoRepository.save(pedido);
    }

    public Optional<PedidoEntity> buscarPedidoPoriD(int idPedido){
        return pedidoRepository.findById(idPedido);
    }

    public List<PedidoEntity> listarTodosPedidos(){
        return pedidoRepository.findAll();
    }

    public PedidoEntity atualizarPedido(PedidoEntity pedido){
        return  pedidoRepository.save(pedido);
    }

    public void cancelarPedido(int idPedido){
        pedidoRepository.deleteById(idPedido);
    }
}
