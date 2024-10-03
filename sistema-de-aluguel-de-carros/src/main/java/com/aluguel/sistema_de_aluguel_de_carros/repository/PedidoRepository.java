package com.aluguel.sistema_de_aluguel_de_carros.repository;

import com.aluguel.sistema_de_aluguel_de_carros.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

}
