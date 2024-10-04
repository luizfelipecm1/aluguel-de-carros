package com.example.alugueldeveiculos.repository;

import com.example.alugueldeveiculos.model.UsuarioEntity;
import com.example.alugueldeveiculos.model.enums.UsuarioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    List<UsuarioEntity> findByNome(String nome);
    List<UsuarioEntity> findByCpf(String cpf);
    List<UsuarioEntity> findByRole(UsuarioEnum role);

}
