package com.aluguel.sistema_de_aluguel_de_carros.repository;

import com.aluguel.sistema_de_aluguel_de_carros.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    List<UsuarioEntity> findByNome(String nome);
    List<UsuarioEntity> findByCpf(String cpf);

}
