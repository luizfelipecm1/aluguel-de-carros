package com.example.alugueldeveiculos.repository;

import com.example.alugueldeveiculos.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

}
